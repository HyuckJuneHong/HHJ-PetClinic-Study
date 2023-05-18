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
import kr.co.hhjpetclinicstudy.persistence.repository.search.VisitSearchRepository;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.*;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.OwnerResDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.VisitResDTO;
import kr.co.hhjpetclinicstudy.service.model.mapper.VisitMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VisitService {

    private final VisitRepository visitRepository;

    private final VisitSearchRepository visitSearchRepository;

    private final PetSearchRepository petSearchRepository;

    private final VetSearchRepository vetSearchRepository;

    private final OwnerSearchRepository ownerSearchRepository;

    private final VisitMapper visitMapper;

    @Transactional
    public void createVisitByOwnerAndPetAndVet(VisitReqDTO.CREATE create) {

        final Owner owner = Optional
                .ofNullable(ownerSearchRepository.searchById(create.getOwnerId()))
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_OWNER_NOT_FOUND));

        final Pet pet = Optional
                .ofNullable(petSearchRepository.searchById(create.getPetId()))
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_PET_NOT_FOUND));

        final Vet vet = Optional
                .ofNullable(vetSearchRepository.searchById(create.getVetId()))
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_VET_NOT_FOUND));

        final Visit visit = visitMapper.toVisitEntity(create, pet, vet, owner);

        visitRepository.save(visit);
    }

    public List<VisitResDTO.READ> getVisitsByOwner(Long ownerId) {

        final Owner owner = Optional
                .ofNullable(ownerSearchRepository.searchById(ownerId))
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_OWNER_NOT_FOUND));

        return visitSearchRepository
                .searchAllByOwner(owner.getId())
                .stream()
                .map(visitMapper::toReadDto)
                .collect(Collectors.toList());
    }

    public List<VisitResDTO.READ> getVisitsByPet(Long petId) {

        final Pet pet = Optional
                .ofNullable(petSearchRepository.searchById(petId))
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_PET_NOT_FOUND));

        return visitSearchRepository
                .searchAllByPet(pet.getId())
                .stream()
                .map(visitMapper::toReadDto)
                .collect(Collectors.toList());
    }

    public List<VisitResDTO.READ> getVisitsByVet(Long vetId) {

        final Vet vet = Optional
                .ofNullable(vetSearchRepository.searchById(vetId))
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_VET_NOT_FOUND));

        return visitSearchRepository
                .searchAllByVet(vet.getId())
                .stream()
                .map(visitMapper::toReadDto)
                .collect(Collectors.toList());
    }

    public List<VisitResDTO.READ_DETAIL> getVisitsByIds(VisitReqDTO.CONDITION condition) {

        final List<Visit> visits = visitSearchRepository.search(condition);

        isEmpty(visits, ResponseStatus.FAIL_VISIT_NOT_FOUND);

        return visits.stream()
                .map(visitMapper::toReadDetailDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateVisitById(Long visitId,
                                VisitReqDTO.UPDATE update) {

        Visit visit = Optional
                .ofNullable(visitSearchRepository.searchById(visitId))
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_VISIT_NOT_FOUND));

        visit.updateVisit(update);
    }

    @Transactional
    public void deleteVisitsByIds(VisitReqDTO.CONDITION condition) {

        final List<Visit> visits = visitSearchRepository.search(condition);

        isEmpty(visits, ResponseStatus.FAIL_VISIT_NOT_FOUND);

        visitRepository.deleteAll(visits);
    }

    private <T> void isEmpty(List<T> list,
                             ResponseStatus responseStatus) {

        if (list.isEmpty()) {
            throw new NotFoundException(responseStatus);
        }
    }
}
