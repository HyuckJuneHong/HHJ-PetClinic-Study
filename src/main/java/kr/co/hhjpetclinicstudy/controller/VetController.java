package kr.co.hhjpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.hhjpetclinicstudy.infrastructure.error.exception.NotFountException;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseFormat;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
import kr.co.hhjpetclinicstudy.service.service.VetService;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VetReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.VetResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vets")
@RequiredArgsConstructor
public class VetController {

    private final VetService vetService;

    /**
     * Vet Create API
     * @param create : info for Create a Vet
     */
    @PostMapping
    public ResponseFormat<Void> createVet(@RequestBody @Valid VetReqDTO.CREATE create){

        try {
            vetService.createVet(create);
            return ResponseFormat.success(ResponseStatus.SUCCESS_CREATE);
        } catch (RuntimeException e){
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * vet By id Read API
     * @return : VetResDTO.READ
     */
    @GetMapping("/{vet_id}")
    public ResponseFormat<VetResDTO.READ> getVetById(@PathVariable(name = "vet_id") Long vetId) {

        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, vetService.getVetById(vetId));
        } catch (NotFountException e){
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e){
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * Vet Update API
     * @param update : Info for Update a Vet
     */
    @PutMapping
    public ResponseFormat<Void> updateVet(@RequestBody VetReqDTO.UPDATE update){

        try {
            vetService.updateVet(update);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFountException e){
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e){
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * Vet Delete API
     * @param vetId : id for delete a vet
     */
    @DeleteMapping("/{vet_id}")
    public ResponseFormat<Void> deleteVetById(@PathVariable(name = "vet_id") Long vetId){

        try {
            vetService.deleteVetById(vetId);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFountException e){
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e){
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }
}
