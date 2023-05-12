package kr.co.hhjpetclinicstudy.controller;

import kr.co.hhjpetclinicstudy.infrastructure.error.exception.NotFoundException;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseFormat;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.PetReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VetReqDTO;
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

    @PostMapping
    public ResponseFormat<Void> createVisitByOwnerAndPetAndVet(@RequestBody @Validated VisitReqDTO.CREATE create) {

        try {
            visitService.createVisitByOwnerAndPetAndVet(create);
            return ResponseFormat.success(ResponseStatus.SUCCESS_CREATE);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

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

    @PostMapping("/search")
    public ResponseFormat<List<VisitResDTO.READ_DETAIL>> getVisitsByIds(@RequestBody VisitReqDTO.CONDITION condition) {

        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, visitService.getVisitsByIds(condition));
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    @PutMapping("/{visit_id}")
    public ResponseFormat<Void> updateVisitById(@PathVariable(name = "visit_id") Long visitId,
                                                @RequestBody @Validated VisitReqDTO.UPDATE update) {

        try {
            visitService.updateVisitById(visitId, update);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseFormat<Void> deleteVisitsByIds(@RequestBody VisitReqDTO.CONDITION condition) {

        try {
            visitService.deleteVisitsByIds(condition);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }
}
