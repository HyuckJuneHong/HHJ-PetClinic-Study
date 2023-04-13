package kr.co.hhjpetclinicstudy.service.service;

import kr.co.hhjpetclinicstudy.infrastructure.error.exception.NotFoundException;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
import kr.co.hhjpetclinicstudy.persistence.entity.Specialty;
import kr.co.hhjpetclinicstudy.persistence.entity.Vet;
import kr.co.hhjpetclinicstudy.persistence.entity.VetSpecialty;
import kr.co.hhjpetclinicstudy.persistence.repository.SpecialtyRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.VetRepository;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VetReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.VetResDTO;
import kr.co.hhjpetclinicstudy.service.model.mappers.SpecialtyMapper;
import kr.co.hhjpetclinicstudy.service.model.mappers.VetMapper;
import kr.co.hhjpetclinicstudy.service.model.mappers.VetSpecialtyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VetService {

    private final VetRepository vetRepository;

    private final SpecialtyRepository specialtyRepository;

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
     * vet update service
     */
    @Transactional
    public void updateVet(VetReqDTO.UPDATE update) {

        Vet vet = vetRepository
                .findById(update.getVetId())
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_NOT_FOUND));

        final List<VetSpecialty> vetSpecialties = getOrCreateVetSpecialties(update.getSpecialtiesName(), vet);

        vet.updateVetSpecialties(vetSpecialties);
    }

    /**
     * vet delete service
     */
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
    private List<Specialty> getOrCreateSpecialtiesByNames(List<String> specialtiesName) {

        List<Specialty> specialties = specialtyRepository.findAllBySpecialtyNameIn(specialtiesName);

        final Set<String> existNames = specialties
                .stream()
                .map(Specialty::getSpecialtyName)
                .collect(Collectors.toSet());

        final List<Specialty> createSpecialties = specialtiesName
                .stream()
                .filter(name -> !existNames.contains(name))
                .map(specialtyMapper::toSpecialtyEntity)
                .collect(Collectors.toList());

        specialties.addAll(createSpecialties);

        return specialties;
    }

    /**
     * vetSpecialty get or create service
     */
    private List<VetSpecialty> getOrCreateVetSpecialties(List<String> specialtiesName,
                                                         Vet vet) {

        final List<Specialty> specialties = getOrCreateSpecialtiesByNames(specialtiesName);

        return specialties
                .stream()
                .map(specialty -> vetspecialtyMapper.toVetSpecialtyEntity(specialty, vet))
                .collect(Collectors.toList());
    }
}
