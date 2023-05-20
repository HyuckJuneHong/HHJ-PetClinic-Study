package kr.co.hhjpetclinicstudy.service.service;

import kr.co.hhjpetclinicstudy.infrastructure.error.exception.NotFoundException;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
import kr.co.hhjpetclinicstudy.persistence.entity.Specialty;
import kr.co.hhjpetclinicstudy.persistence.entity.Vet;
import kr.co.hhjpetclinicstudy.persistence.entity.VetSpecialty;
import kr.co.hhjpetclinicstudy.persistence.repository.SpecialtyRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.VetRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.VetSpecialtyRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.search.VetSpecialtySearchRepository;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.SpecialtyReqDTO;
import kr.co.hhjpetclinicstudy.service.model.mapper.SpecialtyMapper;
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
public class SpecialtyService implements IVetSpecialtyService, ICommonService{

    private final VetSpecialtyRepository vetSpecialtyRepository;

    private final VetRepository vetRepository;

    private final SpecialtyRepository specialtyRepository;

    private final VetSpecialtySearchRepository vetSpecialtySearchRepository;

    private final VetSpecialtyMapper vetSpecialtyMapper;

    private final SpecialtyMapper specialtyMapper;

    public Set<String> getExistSpecialties() {

        final Set<VetSpecialty> vetSpecialties = new HashSet<>(vetSpecialtySearchRepository.searchAll());

        return vetSpecialties
                .stream()
                .map(VetSpecialty::getSpecialty)
                .map(Specialty::getSpecialtyName)
                .collect(Collectors.toSet());
    }

    @Transactional
    public void addSpecialtiesByVet(Long vetId,
                                    SpecialtyReqDTO.UPDATE update) {

        final Vet vet = vetRepository.findById(vetId)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_VET_NOT_FOUND));

        final HashSet<Specialty> specialties = getOrCreateSpecialtiesByNames(update.getSpecialtiesName());

        final List<VetSpecialty> vetSpecialties = specialties.stream()
                .map(specialty -> vetSpecialtyMapper.toVetSpecialtyEntity(vet, specialty))
                .collect(Collectors.toList());

        vetSpecialtyRepository.saveAll(vetSpecialties);
    }

    @Transactional
    public void deleteSpecialtiesByVet(Long vetId,
                                       SpecialtyReqDTO.UPDATE update) {

        final Vet vet = vetRepository.findById(vetId)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_VET_NOT_FOUND));

        final List<VetSpecialty> vetSpecialties = vetSpecialtySearchRepository.searchAll(vet, update.getSpecialtiesName());

        vetSpecialtyRepository.deleteAll(vetSpecialties);

        deleteBySpecialtiesWithoutVet(update.getSpecialtiesName());
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

        return new HashSet<>(createSpecialties);
    }

    @Override
    public void deleteBySpecialtiesWithoutVet(Set<String> specialtiesName) {

        specialtiesName.stream()
                .filter(specialtyName -> !vetSpecialtyRepository.existsBySpecialty_SpecialtyName(specialtyName))
                .forEach(specialtyName -> {
                    Specialty specialty = specialtyRepository.findBySpecialtyName(specialtyName)
                            .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_SPECIALTY_NOT_FOUND));

                    specialtyRepository.delete(specialty);
                });
    }
}
