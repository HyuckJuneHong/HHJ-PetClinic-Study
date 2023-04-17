package kr.co.hhjpetclinicstudy.service.service;

import kr.co.hhjpetclinicstudy.infrastructure.error.exception.NotFoundException;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
import kr.co.hhjpetclinicstudy.persistence.entity.Specialty;
import kr.co.hhjpetclinicstudy.persistence.entity.Vet;
import kr.co.hhjpetclinicstudy.persistence.entity.VetSpecialty;
import kr.co.hhjpetclinicstudy.persistence.repository.SpecialtyRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.VetRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.VetSpecialtyRepository;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VetReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.VetResDTO;
import kr.co.hhjpetclinicstudy.service.model.mapper.SpecialtyMapper;
import kr.co.hhjpetclinicstudy.service.model.mapper.VetMapper;
import kr.co.hhjpetclinicstudy.service.model.mapper.VetSpecialtyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VetService {

    private final VetRepository vetRepository;

    private final SpecialtyRepository specialtyRepository;

    private final VetSpecialtyRepository vetSpecialtyRepository;

    private final VetMapper vetMapper;

    private final SpecialtyMapper specialtyMapper;

    private final VetSpecialtyMapper vetspecialtyMapper;

    /**
     * vet create service
     */
    @Transactional
    public void createVet(VetReqDTO.CREATE create) {

        Vet vet = vetMapper.toVetEntity(create, Collections.emptyList());

        final List<VetSpecialty> vetSpecialties = getOrCreateVetSpecialties(create.getSpecialtiesName(), vet);

        vet.updateVetSpecialties(vetSpecialties);

        vetSpecialtyRepository.saveAll(vetSpecialties);

        vetRepository.save(vet);
    }

    /**
     * vet get by id service
     */
    public VetResDTO.READ getVetById(Long vetId) {

        final Vet vet = vetRepository
                .findById(vetId)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_NOT_FOUND));

        final List<String> specialtiesName = getSpecialtiesNameByVet(vet);

        return vetMapper.toReadDto(vet, specialtiesName);
    }

    /**
     * 펫클리닉에서 수의사들이 가지고 있는 모든 전문분야 반환
     */
    public Set<String> getVetSpecialties() {

        final Set<VetSpecialty> vetSpecialties = new HashSet<>(vetSpecialtyRepository.findAll());

        return vetSpecialties
                .stream()
                .map(VetSpecialty::getSpecialty)
                .map(Specialty::getSpecialtyName)
                .collect(Collectors.toSet());
    }

    @Transactional
    public void addSpecialties(Long vetId,
                               VetReqDTO.ADD_DELETE add) {

        Vet vet = vetRepository
                .findById(vetId)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_NOT_FOUND));

        final List<VetSpecialty> vetSpecialties = getOrCreateVetSpecialties(add.getSpecialtiesName(), vet);

        vet.updateVetSpecialties(vetSpecialties);
    }

    @Transactional
    public void deleteSpecialties(Long vetId,
                                  VetReqDTO.ADD_DELETE delete) {

        final Vet vet = vetRepository
                .findById(vetId)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_NOT_FOUND));

        final Set<VetSpecialty> vetSpecialties = vetSpecialtyRepository
                .findByVetAndSpecialty_SpecialtyNameIn(vet, delete.getSpecialtiesName());

        vetSpecialtyRepository.deleteAll(vetSpecialties);

        deleteBySpecialtiesWithoutVet(delete.getSpecialtiesName());
    }

    /**
     * vet delete service
     */
    @Transactional
    public void deleteVetById(Long vetId) {

        final Vet vet = vetRepository.findById(vetId)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_NOT_FOUND));

        vetRepository.delete(vet);
    }

    /**
     * specialties name by vet service
     */
    private List<String> getSpecialtiesNameByVet(Vet vet) {

        return vet
                .getVetSpecialties()
                .stream()
                .map(VetSpecialty::getSpecialty)
                .map(Specialty::getSpecialtyName)
                .collect(Collectors.toList());
    }

    /**
     * specialty get or create service
     */
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

    /**
     * vetSpecialty get or create service
     */
    private List<VetSpecialty> getOrCreateVetSpecialties(Set<String> specialtiesName,
                                                         Vet vet) {

        final Set<Specialty> specialties = getOrCreateSpecialtiesByNames(specialtiesName);

        return specialties
                .stream()
                .map(specialty -> vetspecialtyMapper.toVetSpecialtyEntity(specialty, vet))
                .collect(Collectors.toList());
    }

    private void deleteBySpecialtiesWithoutVet(Set<String> specialtiesName) {

        specialtiesName
                .stream()
                .filter(specialtyName -> vetSpecialtyRepository.countBySpecialtyName(specialtyName) == 0)
                .map(specialtyName -> specialtyRepository
                        .findBySpecialtyName(specialtyName)
                        .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_NOT_FOUND)))
                .forEach(specialtyRepository::delete);
    }
}
