package kr.co.hhjpetclinicstudy.service.service;

import kr.co.hhjpetclinicstudy.persistence.entity.Specialty;
import kr.co.hhjpetclinicstudy.persistence.entity.Vet;
import kr.co.hhjpetclinicstudy.persistence.entity.VetSpecialty;
import kr.co.hhjpetclinicstudy.persistence.repository.SpecialtyRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.VetRepository;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VetReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.VetResDTO;
import kr.co.hhjpetclinicstudy.service.model.mappers.SpecialtyMappers;
import kr.co.hhjpetclinicstudy.service.model.mappers.VetMappers;
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

    private final VetMappers vetMappers;

    private final SpecialtyMappers specialtyMappers;

    /**
     * vet create service
     * @param create : Info for create a vet
     */
    @Transactional
    public void createVet(VetReqDTO.CREATE create) {

        Vet vet = vetMappers.toVetEntity(create, Collections.emptyList());

        final List<VetSpecialty> vetSpecialties = getOrCreateVetSpecialties(create.getSpecialtiesName(), vet);

        vet.updateVetSpecialties(vetSpecialties);

        vetRepository.save(vet);
    }

    /**
     * vet get by id service
     * @return : Vet
     */
    public VetResDTO.READ getVetById(Long vetId) {

        final Vet vet = vetRepository.findById(vetId)
                .orElseThrow(() -> new RuntimeException("Not Found Vet"));

        final List<String> specialtiesName = getSpecialtiesNameByVet(vet);

        return vetMappers.toReadDto(vet, specialtiesName);
    }

    /**
     * vet update service
     * @param update : info for update a vet
     */
    @Transactional
    public void updateVet(VetReqDTO.UPDATE update) {

        Vet vet = vetRepository.findById(update.getVetId())
                .orElseThrow(() -> new RuntimeException("Not Found Vet"));

        final List<VetSpecialty> vetSpecialties = getOrCreateVetSpecialties(update.getSpecialtiesName(), vet);

        vet.updateVetSpecialties(vetSpecialties);

        vetRepository.save(vet);
    }

    /**
     * vet delete service
     * @param vetId : id for delete
     */
    public void deleteVetById(Long vetId) {

        final Vet vet = vetRepository.findById(vetId)
                .orElseThrow(() -> new RuntimeException("Not Found Vet"));

        vetRepository.delete(vet);
    }

    /**
     * specialties name by vet service
     * @return String List
     */
    private List<String> getSpecialtiesNameByVet(Vet vet){

        return vet.getVetSpecialties().stream()
                .map(VetSpecialty::getSpecialty)
                .map(Specialty::getName)
                .collect(Collectors.toList());
    }

    /**
     * specialty get or create service
     * @param names : find names
     * @return : Specialties
     */
    private List<Specialty> getOrCreateSpecialtiesByNames(List<String> names){

        List<Specialty> specialties = specialtyRepository.findAllByName(names);

        final Set<String> existNames = specialties.stream()
                .map(Specialty::getName)
                .collect(Collectors.toSet());

        final List<Specialty> createSpecialties = names.stream()
                .filter(name -> !existNames.contains(name))
                .map(specialtyMappers::toSpecialtyEntity)
                .collect(Collectors.toList());

        specialties.addAll(createSpecialties);

        return specialties;
    }

    /**
     * vetSpecialty get or create service
     * @param names : find names
     * @param vet : vet
     * @return : vetSpecialties
     */
    private List<VetSpecialty> getOrCreateVetSpecialties(List<String> names,
                                                         Vet vet){

        final List<Specialty> specialties = getOrCreateSpecialtiesByNames(names);

        return specialties.stream()
                .map(specialty -> VetSpecialty.builder()
                        .specialty(specialty)
                        .vet(vet)
                        .build())
                .collect(Collectors.toList());
    }
}
