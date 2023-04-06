package kr.co.hhjpetclinicstudy.service.service;

import kr.co.hhjpetclinicstudy.persistence.entity.Pet;
import kr.co.hhjpetclinicstudy.persistence.entity.Visit;
import kr.co.hhjpetclinicstudy.persistence.repository.PetRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.VisitRepository;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VisitReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.VisitResDTO;
import kr.co.hhjpetclinicstudy.service.model.mappers.VisitMappers;
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

    private final VisitMappers visitMappers;

    /**
     * Visit Create Service
     * @param create : Info for create a visit
     */
    @Transactional
    public void createVisit(VisitReqDTO.CREATE create) {

        final Pet pet = petRepository.findById(create.getPetId())
                .orElseThrow(() -> new RuntimeException("Not Found Pet"));

        final Visit visit = visitMappers.toVisitEntity(create, pet);

        visitRepository.save(visit);
    }

    /**
     * visit get by id service
     * @return : VisitResDTO.READ
     */
    public VisitResDTO.READ getVisitById(Long visitId) {

        final Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new RuntimeException(("Not Found Visit")));

        return visitMappers.toReadDto(visit);
    }

    /**
     * visit get by pet service
     * @return : List VisitResDTO.READ
     */
    public List<VisitResDTO.READ> getVisitsByPet(Long petId) {

        final Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Not Found Pet"));

        return visitRepository.findByPet(pet).stream()
                .map(visitMappers::toReadDto)
                .collect(Collectors.toList());
    }

    /**
     * visit update service
     * @param update : Info for Update a visit
     */
    @Transactional
    public void updateVisit(VisitReqDTO.UPDATE update) {

        Visit visit = visitRepository.findById(update.getVisitId())
                .orElseThrow(() -> new RuntimeException("Not Found Visit"));

        visit.updateVisit(update);
    }
}
