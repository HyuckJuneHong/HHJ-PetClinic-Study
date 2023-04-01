package kr.co.hhjpetclinicstudy.service;

import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.persistence.entity.Pet;
import kr.co.hhjpetclinicstudy.persistence.repository.OwnerRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.PetRepository;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.PetReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.PetResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PetService {
    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;

    /**
     * pet create service
     * @param create : Info for Create a Pet
     */
    @Transactional
    public void createPet(PetReqDTO.CREATE create) {
        final Owner owner = ownerRepository.findById(create.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Not Found Owner"));

        final Pet pet = Pet.dtoToEntity(create, owner);

        petRepository.save(pet);
    }

    /**
     * pet get by all service
     * @return List PetResDTO.READ
     */
    public List<PetResDTO.READ> getPetsByAll() {
        return petRepository.findAll().stream().map(Pet::entityToDto).collect(Collectors.toList());
    }
}
