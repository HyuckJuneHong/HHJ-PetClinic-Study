package kr.co.hhjpetclinicstudy.service;

import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.persistence.entity.Pet;
import kr.co.hhjpetclinicstudy.persistence.repository.OwnerRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.PetRepository;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.PetReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PetService {
    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;

    @Transactional
    public void createPet(PetReqDTO.CREATE create) {
        final Owner owner = ownerRepository.findById(create.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Not Found Owner"));

        final Pet pet = Pet.dtoToEntity(create, owner);

        petRepository.save(pet);
    }
}
