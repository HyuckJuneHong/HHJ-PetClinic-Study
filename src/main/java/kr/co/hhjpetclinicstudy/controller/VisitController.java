package kr.co.hhjpetclinicstudy.controller;

import kr.co.hhjpetclinicstudy.infrastructure.error.exception.NotFoundException;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseFormat;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VisitReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.VisitResDTO;
import kr.co.hhjpetclinicstudy.service.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visits")
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;

    /**
     * Visit Create API
     *
     * @param create : info for Create a visit
     */
    @PostMapping
    public ResponseFormat<Void> createVisit(@RequestBody @Validated VisitReqDTO.CREATE create) {

        try {
            visitService.createVisit(create);
            return ResponseFormat.success(ResponseStatus.SUCCESS_CREATE);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * 소유자에 대한 방문 기록들 조회
     */
    @GetMapping("/owners/{owner_id}")
    public ResponseFormat<List<VisitResDTO.READ>> getVisitsByOwner(@PathVariable(name = "owner_id") Long ownerId) {

        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, visitService.getVisitsByOwner(ownerId));
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * 애완동물에 대해 방문 기록들 조회
     */
    @GetMapping("/pets/{pet_id}")
    public ResponseFormat<List<VisitResDTO.READ>> getVisitsByPet(@PathVariable(name = "pet_id") Long petId) {

        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, visitService.getVisitsByPet(petId));
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * 수의사 담당 애완동물들 조회
     */
    @GetMapping("/vets/{vet_id}")
    public ResponseFormat<List<VisitResDTO.READ>> getVisitsByVet(@PathVariable(name = "vet_id") Long vetId){

        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, visitService.getVisitsByVet(vetId));
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * 방문 기록 상세 조회
     */
    @GetMapping("/{visit_id}")
    public ResponseFormat<VisitResDTO.READ_DETAIL> getVisitById(@PathVariable(name = "visit_id") Long visitId) {

        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, visitService.getVisitById(visitId));
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseFormat<Void> updateVisit(@PathVariable(name = "visit_id") Long visitId,
                                            @RequestBody @Validated VisitReqDTO.UPDATE update) {

        try {
            visitService.updateVisit(visitId, update);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * Visit Delete API
     *
     * @param visitId : id for delete a visit
     */
    @DeleteMapping("/{visit_id}")
    public ResponseFormat<Void> deleteVisitById(@PathVariable(name = "visit_id") Long visitId) {

        try {
            visitService.deleteVisitById(visitId);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }
}
