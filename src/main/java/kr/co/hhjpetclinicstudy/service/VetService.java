package kr.co.hhjpetclinicstudy.service;

import kr.co.hhjpetclinicstudy.persistence.entity.Specialty;
import kr.co.hhjpetclinicstudy.persistence.entity.Vet;
import kr.co.hhjpetclinicstudy.persistence.repository.SpecialtyRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.VetRepository;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VetReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VetService {

    private final VetRepository vetRepository;

    private final SpecialtyRepository specialtyRepository;

    /**
     * vet create service
     * @param create : Info for create a vet
     */
    @Transactional
    public void createVet(VetReqDTO.CREATE create) {

        final List<Specialty> specialtyList = create.getSpecialtiesId().stream()
                .map(specialtyId -> specialtyRepository.findById(specialtyId)
                        .orElseThrow(() -> new RuntimeException("Not Found Specialty")))
                .collect(Collectors.toList());

        final Vet vet = Vet.dtoToEntity(create, specialtyList);

        vetRepository.save(vet);
    }
}
