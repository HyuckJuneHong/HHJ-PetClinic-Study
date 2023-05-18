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
import kr.co.hhjpetclinicstudy.service.model.dtos.request.SpecialtyReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VetReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.VetResDTO;
import kr.co.hhjpetclinicstudy.service.model.mapper.SpecialtyMapper;
import kr.co.hhjpetclinicstudy.service.model.mapper.VetMapper;
import kr.co.hhjpetclinicstudy.service.model.mapper.VetSpecialtyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VetService {

    private final VetRepository vetRepository;

    private final SpecialtyRepository specialtyRepository;

    private final VetSpecialtyRepository vetSpecialtyRepository;

    private final VetSearchRepository vetSearchRepository;

    private final VetSpecialtySearchRepository vetSpecialtySearchRepository;

    private final VetMapper vetMapper;

    private final SpecialtyMapper specialtyMapper;

    private final VetSpecialtyMapper vetspecialtyMapper;

    @Transactional
    public void createVetAndSpecialties(VetReqDTO.CREATE create) {

        Vet vet = vetMapper.toVetEntity(create, Collections.emptyList());

        final List<VetSpecialty> vetSpecialties = getOrCreateVetSpecialties(create.getSpecialtiesName(), vet);

        vet.updateVetSpecialties(vetSpecialties);

        vetSpecialtyRepository.saveAll(vetSpecialties);

        vetRepository.save(vet);
    }

    public VetResDTO.READ getVetsById(Long vetId) {

        final Vet vet = Optional
                .ofNullable(vetSearchRepository.searchById(vetId))
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_NOT_FOUND));

        final List<String> specialtiesName = getSpecialtiesNameByVet(vet);

        return vetMapper.toReadDto(vet, specialtiesName);
    }

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

        Vet vet = Optional
                .ofNullable(vetSearchRepository.searchById(vetId))
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_VET_NOT_FOUND));

        final List<VetSpecialty> vetSpecialties = getOrCreateVetSpecialties(update.getSpecialtiesName(), vet);

        vet.updateVetSpecialties(vetSpecialties);
    }

    @Transactional
    public void deleteSpecialtiesByVet(Long vetId,
                                       SpecialtyReqDTO.UPDATE update) {

        final Vet vet = Optional
                .ofNullable(vetSearchRepository.searchById(vetId))
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_VET_NOT_FOUND));

        final List<VetSpecialty> vetSpecialties = vetSpecialtySearchRepository.searchAll(vet, update.getSpecialtiesName());

        vetSpecialtyRepository.deleteAll(vetSpecialties);

        deleteBySpecialtiesWithoutVet(update.getSpecialtiesName());
    }

    @Transactional
    public void deleteVetsByIds(VetReqDTO.CONDITION condition) {

        final List<Vet> vets = vetSearchRepository.search(condition);

        isEmpty(vets, ResponseStatus.FAIL_VET_NOT_FOUND);

        vetRepository.deleteAll(vets);
    }

    private List<String> getSpecialtiesNameByVet(Vet vet) {

        return vet
                .getVetSpecialties()
                .stream()
                .map(vetSpecialty -> vetSpecialty.getSpecialty().getSpecialtyName())
                .collect(Collectors.toList());
    }

    private Set<Specialty> getOrCreateSpecialtiesByNames(Set<String> specialtiesNames) {

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

    private List<VetSpecialty> getOrCreateVetSpecialties(Set<String> specialtiesName,
                                                         Vet vet) {

        final Set<Specialty> specialties = getOrCreateSpecialtiesByNames(specialtiesName);

        return specialties.stream()
                .map(specialty -> vetspecialtyMapper.toVetSpecialtyEntity(specialty, vet))
                .collect(Collectors.toList());
    }

    private void deleteBySpecialtiesWithoutVet(Set<String> specialtiesName) {

        specialtiesName.stream()
                .filter(specialtyName -> vetSpecialtySearchRepository
                        .searchCountBySpecialtyName(specialtyName) == 0)

                .map(specialtyName -> specialtyRepository
                        .findBySpecialtyName(specialtyName)
                        .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_NOT_FOUND)))

                .forEach(specialtyRepository::delete);
    }

    private <T> void isEmpty(List<T> list,
                             ResponseStatus responseStatus) {

        if (list.isEmpty()) {
            throw new NotFoundException(responseStatus);
        }
    }
}
