package kr.co.hhjpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.hhjpetclinicstudy.infrastructure.error.exception.NotFoundException;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseFormat;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.PetReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.SpecialtyReqDTO;
import kr.co.hhjpetclinicstudy.service.service.VetService;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VetReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.VetResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/vets")
@RequiredArgsConstructor
public class VetController {

    private final VetService vetService;

    @PostMapping
    public ResponseFormat<Void> createVetAndSpecialties(@RequestBody @Valid VetReqDTO.CREATE create) {

        try {
            vetService.createVetAndSpecialties(create);
            return ResponseFormat.success(ResponseStatus.SUCCESS_CREATE);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    @GetMapping("/{vet_id}")
    public ResponseFormat<VetResDTO.READ> getVetsById(@PathVariable(name = "vet_id") Long vetId) {

        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, vetService.getVetsById(vetId));
        } catch (NotFoundException e) {
            return ResponseFormat.error(e.toString(), ResponseStatus.FAIL_NOT_FOUND.getStatusCode());
        } catch (RuntimeException e) {
            return ResponseFormat.error(e.toString(), ResponseStatus.FAIL_BAD_REQUEST.getStatusCode());
        }
    }

    @GetMapping("/specialties")
    public ResponseFormat<Set<String>> getExistSpecialties() {

        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, vetService.getExistSpecialties());
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    @PutMapping("/specialties/{vet_id}")
    public ResponseFormat<Void> addSpecialtiesByVet(@PathVariable(name = "vet_id") Long vetId,
                                                    @RequestBody SpecialtyReqDTO.UPDATE update) {

        try {
            vetService.addSpecialtiesByVet(vetId, update);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    @DeleteMapping("/specialties/{vet_id}")
    public ResponseFormat<Void> deleteSpecialtiesByVet(@PathVariable(name = "vet_id") Long vetId,
                                                       @RequestBody SpecialtyReqDTO.UPDATE update) {

        try {
            vetService.deleteSpecialtiesByVet(vetId, update);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseFormat<Void> deleteVetsByIds(@RequestBody VetReqDTO.CONDITION condition) {

        try {
            vetService.deleteVetsByIds(condition);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }
}
