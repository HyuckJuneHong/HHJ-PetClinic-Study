package kr.co.hhjpetclinicstudy.service.service;

import kr.co.hhjpetclinicstudy.infrastructure.error.exception.NotFoundException;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
import kr.co.hhjpetclinicstudy.persistence.entity.Specialty;
import kr.co.hhjpetclinicstudy.persistence.entity.Vet;
import kr.co.hhjpetclinicstudy.persistence.entity.VetSpecialty;
import kr.co.hhjpetclinicstudy.persistence.repository.SpecialtyRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.VetRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.VetSpecialtyRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.search.VetSearchRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.search.VetSpecialtySearchRepository;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VetReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VetSpecialtyReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.VetResDTO;
import kr.co.hhjpetclinicstudy.service.model.mapper.SpecialtyMapper;
import kr.co.hhjpetclinicstudy.service.model.mapper.VetMapper;
import kr.co.hhjpetclinicstudy.service.model.mapper.VetSpecialtyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VetService implements IVetSpecialtyService, ICommonService{

    private final VetRepository vetRepository;

    private final SpecialtyRepository specialtyRepository;

    private final VetSpecialtyRepository vetSpecialtyRepository;

    private final VetSearchRepository vetSearchRepository;

    private final VetSpecialtySearchRepository vetSpecialtySearchRepository;

    private final VetMapper vetMapper;

    private final SpecialtyMapper specialtyMapper;

    private final VetSpecialtyMapper vetSpecialtyMapper;

    @Transactional
    public void createVetAndSpecialties(VetSpecialtyReqDTO.CREATE create) {

        final Vet vet = vetMapper.toVetEntity(create.getFirstName(), create.getLastName());

        final HashSet<Specialty> specialties = getOrCreateSpecialtiesByNames(create.getSpecialtiesName());

        final List<VetSpecialty> vetSpecialties = specialties.stream()
                .map(specialty -> vetSpecialtyMapper.toVetSpecialtyEntity(vet, specialty))
                .collect(Collectors.toList());

        vetRepository.save(vet);

        vetSpecialtyRepository.saveAll(vetSpecialties);
    }

    public VetResDTO.READ getVetById(Long vetId) {

        final List<VetSpecialty> vetSpecialties = vetSpecialtySearchRepository.searchAllByVetId(vetId);

        isEmpty(vetSpecialties, ResponseStatus.FAIL_VET_NOT_FOUND);

        final List<String> specialtiesName = vetSpecialties.stream()
                .map(vetSpecialty -> vetSpecialty.getSpecialty().getSpecialtyName())
                .collect(Collectors.toList());

        return vetMapper.toReadDto(vetSpecialties.get(0).getVet(), specialtiesName);
    }

    @Transactional
    public void deleteVetsByIds(VetReqDTO.CONDITION condition) {

        final List<Vet> vets = vetSearchRepository.search(condition);

        isEmpty(vets, ResponseStatus.FAIL_VET_NOT_FOUND);

        final List<VetSpecialty> vetSpecialties = vetSpecialtySearchRepository.searchAll(vets);

        isEmpty(vetSpecialties, ResponseStatus.FAIL_VET_SPECIALTY_NOT_FOUND);

        final Set<String> specialtiesName = vetSpecialties.stream()
                .map(vetSpecialty -> vetSpecialty.getSpecialty().getSpecialtyName())
                .collect(Collectors.toSet());

        vetSpecialtyRepository.deleteAll(vetSpecialties);

        vetRepository.deleteAll(vets);

        deleteBySpecialtiesWithoutVet(specialtiesName);
    }

    @Override
    public HashSet<Specialty> getOrCreateSpecialtiesByNames(Set<String> specialtiesNames) {

        List<Specialty> specialties = specialtyRepository.findAllBySpecialtyNameIn(specialtiesNames);

        final Set<String> existNames = specialties
                .stream()
                .map(Specialty::getSpecialtyName)
                .collect(Collectors.toSet());

        final List<Specialty> createSpecialties = specialtiesNames
                .stream()
                .filter(name -> !existNames.contains(name))
                .map(specialtyMapper::toSpecialtyEntity)
                .collect(Collectors.toList());

        specialtyRepository.saveAll(createSpecialties);

        specialties.addAll(createSpecialties);

        return new HashSet<>(specialties);
    }

    @Override
    public void deleteBySpecialtiesWithoutVet(Set<String> specialtiesName) {

        specialtiesName.stream()
                .filter(specialtyName -> !vetSpecialtyRepository.existsBySpecialty_SpecialtyName(specialtyName))
                .forEach(specialtyName -> {
                    Specialty specialty = specialtyRepository.findBySpecialtyName(specialtyName)
                            .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_NOT_FOUND));

                    specialtyRepository.delete(specialty);
                });
    }
}
