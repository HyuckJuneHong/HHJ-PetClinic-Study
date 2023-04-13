package kr.co.hhjpetclinicstudy.service.service;

import kr.co.hhjpetclinicstudy.infrastructure.error.exception.NotFoundException;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.persistence.entity.Pet;
import kr.co.hhjpetclinicstudy.persistence.repository.OwnerRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.PetRepository;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.PetReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.PetResDTO;
import kr.co.hhjpetclinicstudy.service.model.mappers.PetMapper;
import lombok.RequiredArgsConstructor;
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

    private final PetMapper petMapper;

    /**
     * pet create service
     */
    @Transactional
    public void createPet(PetReqDTO.CREATE create) {

        final Owner owner = ownerRepository
                .findById(create.getOwnerId())
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_NOT_FOUND));

        final Pet pet = petMapper.toPetEntity(create, owner);

        petRepository.save(pet);
    }

    /**
     * pets get by owner service
     */
    public List<PetResDTO.READ> getPetsByOwner(Long ownerId) {

        final Owner owner = ownerRepository
                .findById(ownerId)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_NOT_FOUND));

        return petRepository
                .findByOwner(owner)
                .stream()
                .map(petMapper::toReadDto)
                .collect(Collectors.toList());
    }

    /**
     * pet update service
     */
    @Transactional
    public void updatePet(PetReqDTO.UPDATE update) {

        Pet pet = petRepository
                .findById(update.getPetId())
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_NOT_FOUND));

        pet.updatePetInfo(update);
    }

    /**
     * pet delete service
     */
    @Transactional
    public void deletePetById(Long petId) {

        final Pet pet = petRepository
                .findById(petId)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_NOT_FOUND));

        petRepository.delete(pet);
    }
}
