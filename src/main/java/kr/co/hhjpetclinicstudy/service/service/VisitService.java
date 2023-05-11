package kr.co.hhjpetclinicstudy.service.service;

import kr.co.hhjpetclinicstudy.infrastructure.error.exception.InvalidRequestException;
import kr.co.hhjpetclinicstudy.infrastructure.error.exception.NotFoundException;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.persistence.entity.Pet;
import kr.co.hhjpetclinicstudy.persistence.entity.Vet;
import kr.co.hhjpetclinicstudy.persistence.entity.Visit;
import kr.co.hhjpetclinicstudy.persistence.repository.OwnerRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.PetRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.VetRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.VisitRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.search.OwnerSearchRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.search.PetSearchRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.search.VetSearchRepository;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.*;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.VisitResDTO;
import kr.co.hhjpetclinicstudy.service.model.mapper.VisitMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VisitService {

    private final VisitRepository visitRepository;

    private final PetRepository petRepository;

    private final VetRepository vetRepository;

    private final OwnerRepository ownerRepository;

    private final PetSearchRepository petSearchRepository;

    private final VetSearchRepository vetSearchRepository;

    private final OwnerSearchRepository ownerSearchRepository;

    private final VisitMapper visitMapper;

    @Transactional
    public void createVisit(VisitReqDTO.CREATE create) {

        final PetReqDTO.CONDITION petId = PetReqDTO.CONDITION.builder()
                .petId(create.getPetId())
                .build();

        final VetReqDTO.CONDITION vetId = VetReqDTO.CONDITION.builder()
                .vetId(create.getVetId())
                .build();

        final OwnerReqDTO.CONDITION ownerId = OwnerReqDTO.CONDITION.builder()
                .ownerId(create.getOwnerId())
                .build();

        final Pet pet = isPets(petSearchRepository.search(petId));

        final Vet vet = isVets(vetSearchRepository.search(vetId));

        final Owner owner = isOwners(ownerSearchRepository.search(ownerId));

        final Visit visit = visitMapper.toVisitEntity(create, pet, vet, owner);

        visitRepository.save(visit);
    }

    /**
     * visit get by pet service
     */
    public List<VisitResDTO.READ> getVisitsByPet(Long petId) {

        final Pet pet = petRepository
                .findById(petId)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_NOT_FOUND));

        return visitRepository
                .findByPetId(pet.getId())
                .stream()
                .map(visitMapper::toReadDto)
                .collect(Collectors.toList());
    }

    /**
     * visit get by owner service
     */
    public List<VisitResDTO.READ> getVisitsByOwner(Long ownerId) {

        final Owner owner = ownerRepository
                .findById(ownerId)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_NOT_FOUND));

        return visitRepository
                .findByOwnerId(owner.getId())
                .stream()
                .map(visitMapper::toReadDto)
                .collect(Collectors.toList());
    }

    public List<VisitResDTO.READ> getVisitsByVet(Long vetId) {

        final Vet vet = vetRepository
                .findById(vetId)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_NOT_FOUND));

        return visitRepository
                .findByVetId(vet.getId())
                .stream()
                .map(visitMapper::toReadDto)
                .collect(Collectors.toList());
    }

    /**
     * visit get by id service
     */
    public VisitResDTO.READ_DETAIL getVisitById(Long visitId) {

        final Visit visit = visitRepository
                .findById(visitId)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_NOT_FOUND));

        return visitMapper.toReadDetailDto(visit);
    }

    @Transactional
    public void updateVisit(Long visitId,
                            VisitReqDTO.UPDATE update) {

        Visit visit = visitRepository
                .findById(visitId)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_NOT_FOUND));

        visit.updateVisit(update);
    }

    /**
     * visit delete service
     *
     * @param visitId : id for delete a visit
     */
    @Transactional
    public void deleteVisitById(Long visitId) {

        final Visit visit = visitRepository
                .findById(visitId)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_NOT_FOUND));

        visitRepository.delete(visit);
    }

    private Vet isVets(List<Vet> vets) {

        if (vets.size() != 1) {
            throw new InvalidRequestException(ResponseStatus.FAIL_BAD_REQUEST);
        }

        return vets.get(0);
    }

    private Pet isPets(List<Pet> pets) {

        if (pets.size() != 1) {
            throw new InvalidRequestException(ResponseStatus.FAIL_BAD_REQUEST);
        }

        return pets.get(0);
    }

    private Owner isOwners(List<Owner> owners) {

        if (owners.size() != 1) {
            throw new InvalidRequestException(ResponseStatus.FAIL_BAD_REQUEST);
        }

        return owners.get(0);
    }
}
