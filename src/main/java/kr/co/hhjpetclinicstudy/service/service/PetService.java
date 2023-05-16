package kr.co.hhjpetclinicstudy.service.service;

import kr.co.hhjpetclinicstudy.infrastructure.error.exception.NotFoundException;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.persistence.entity.Pet;
import kr.co.hhjpetclinicstudy.persistence.repository.PetRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.search.OwnerSearchRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.search.PetSearchRepository;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.PetReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.PetResDTO;
import kr.co.hhjpetclinicstudy.service.model.mapper.PetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PetService {

    private final PetRepository petRepository;

    private final PetSearchRepository petSearchRepository;

    private final OwnerSearchRepository ownerSearchRepository;

    private final PetMapper petMapper;

    /**
     * pet create service
     */
    @Transactional
    public void createPetByOwner(Long ownerId,
                                 PetReqDTO.CREATE create) {

        final Owner owner = Optional
                .ofNullable(ownerSearchRepository.searchById(ownerId))
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_OWNER_NOT_FOUND));

        final Pet pet = petMapper.toPetEntity(create, owner);

        petRepository.save(pet);
    }

    public List<PetResDTO.READ_DETAIL> getPetsByIds(PetReqDTO.CONDITION condition) {

        final List<Pet> pets = petSearchRepository.search(condition);

        isEmpty(pets, ResponseStatus.FAIL_PET_NOT_FOUND);

        return pets.stream()
                .map(petMapper::toReadDetailDto)
                .collect(Collectors.toList());
    }

    public List<PetResDTO.READ> getPetsByOwner(PetReqDTO.CONDITION condition) {

        final List<Pet> pets = petSearchRepository.search(condition);

        isEmpty(pets, ResponseStatus.FAIL_PET_NOT_FOUND);

        return pets
                .stream()
                .map(petMapper::toReadDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updatePetById(Long petId,
                              PetReqDTO.UPDATE update) {

        Pet pet = Optional
                .ofNullable(petSearchRepository.searchById(petId))
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_PET_NOT_FOUND));

        pet.updatePetInfo(update);
    }

    @Transactional
    public void deletePetsByIds(PetReqDTO.CONDITION condition) {

        final List<Pet> pets = petSearchRepository.search(condition);

        isEmpty(pets, ResponseStatus.FAIL_PET_NOT_FOUND);

        petRepository.deleteAll(pets);
    }

    private <T> void isEmpty(List<T> list,
                            ResponseStatus responseStatus) {

        if (list.isEmpty()) {
            throw new NotFoundException(responseStatus);
        }
    }
}
