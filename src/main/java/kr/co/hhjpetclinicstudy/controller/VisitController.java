package kr.co.hhjpetclinicstudy.controller;

import kr.co.hhjpetclinicstudy.infrastructure.error.exception.NotFountException;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseFormat;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VisitReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.VisitResDTO;
import kr.co.hhjpetclinicstudy.service.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
     * @param create : info for Create a visit
     */
    @PostMapping
    public ResponseFormat<Void> createVisit(@RequestBody @Validated VisitReqDTO.CREATE create){

        try {
            visitService.createVisit(create);
            return ResponseFormat.success(ResponseStatus.SUCCESS_CREATE);
        } catch (NotFountException e){
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e){
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * Visit By Pet Read API
     * @return : List VisitResDTO.READ
     */
    @GetMapping("/{pet_id}")
    public ResponseFormat<List<VisitResDTO.READ>> getVisitsByPet(@PathVariable(name = "pet_id") Long petId) {

        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, visitService.getVisitsByPet(petId));
        } catch (NotFountException e){
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e){
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * Visit By id Read API
     * @return : VisitResDTO.READ
     */
    @GetMapping("/{pet_id}/{visit_id}")
    public ResponseFormat<VisitResDTO.READ> getVisitById(@PathVariable(name = "visit_id") Long visitId,
                                                         @PathVariable(name = "pet_id") Long petId) {

        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, visitService.getVisitById(visitId));
        } catch (NotFountException e){
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e){
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * Visit Update API
     * @param update : Info for Update a Visit
     */
    @PutMapping
    public ResponseFormat<Void> updateVisit(@RequestBody @Validated VisitReqDTO.UPDATE update){

        try {
            visitService.updateVisit(update);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFountException e){
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e){
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }


    /**
     * Visit Delete API
     * @param visitId : id for delete a visit
     */
    @DeleteMapping("/{visit_id}")
    public ResponseFormat<Void> deleteVisitById(@PathVariable(name = "visit_id") Long visitId){

        try {
            visitService.deleteVisitById(visitId);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFountException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e){
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }
}
