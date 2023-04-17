package kr.co.hhjpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.hhjpetclinicstudy.infrastructure.error.exception.NotFoundException;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseFormat;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
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

    /**
     * Vet Create API
     *
     * @param create : info for Create a Vet
     */
    @PostMapping
    public ResponseFormat<Void> createVet(@RequestBody @Valid VetReqDTO.CREATE create) {

        try {
            vetService.createVet(create);
            return ResponseFormat.success(ResponseStatus.SUCCESS_CREATE);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * 수의사 정보 조회
     */
    @GetMapping("/{vet_id}")
    public ResponseFormat<VetResDTO.READ> getVetById(@PathVariable(name = "vet_id") Long vetId) {

        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, vetService.getVetById(vetId));
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * 수의사들이 가진 모든 전문분야 조회
     */
    @GetMapping("/specialties")
    public ResponseFormat<Set<String>> getVetSpecialties() {

        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, vetService.getVetSpecialties());
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    @PutMapping("/specialties/{vet_id}")
    public ResponseFormat<Void> addSpecialties(@PathVariable(name = "vet_id") Long vetId,
                                               @RequestBody VetReqDTO.ADD_DELETE add) {

        try {
            vetService.addSpecialties(vetId, add);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    @DeleteMapping("/specialties/{vet_id}")
    public ResponseFormat<Void> deleteSpecialties(@PathVariable(name = "vet_id") Long vetId,
                                                  @RequestBody VetReqDTO.ADD_DELETE delete) {

        try {
            vetService.deleteSpecialties(vetId, delete);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * Vet Delete API
     *
     * @param vetId : id for delete a vet
     */
    @DeleteMapping("/{vet_id}")
    public ResponseFormat<Void> deleteVetById(@PathVariable(name = "vet_id") Long vetId) {

        try {
            vetService.deleteVetById(vetId);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }
}
