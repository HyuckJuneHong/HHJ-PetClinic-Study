package kr.co.hhjpetclinicstudy.service.service;

import kr.co.hhjpetclinicstudy.persistence.entity.Pet;
import kr.co.hhjpetclinicstudy.persistence.entity.Visit;
import kr.co.hhjpetclinicstudy.persistence.repository.PetRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.VisitRepository;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VisitReqDTO;
import kr.co.hhjpetclinicstudy.service.model.mappers.VisitMappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;

    private final PetRepository petRepository;

    private final VisitMappers visitMappers;

    /**
     * Visit Create Service
     * @param create : Info for create a visit
     */
    public void createVisit(VisitReqDTO.CREATE create) {

        final Pet pet = petRepository.findById(create.getPetId())
                .orElseThrow(() -> new RuntimeException("Not Found Pet"));

        final Visit visit = visitMappers.toVisitEntity(create, pet);

        visitRepository.save(visit);
    }
}
