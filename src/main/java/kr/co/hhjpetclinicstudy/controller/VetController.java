package kr.co.hhjpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.hhjpetclinicstudy.infrastructure.error.exception.NotFoundException;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseFormat;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.PetReqDTO;
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
    public ResponseFormat<Void> createVet(@RequestBody @Valid VetReqDTO.CREATE create) {

        try {
            vetService.createVet(create);
            return ResponseFormat.success(ResponseStatus.SUCCESS_CREATE);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    @PostMapping("/search")
    public ResponseFormat<VetResDTO.READ> getVetsById(@RequestBody VetReqDTO.CONDITION condition) {

        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, vetService.getVetsById(condition));
        } catch (NotFoundException e) {
            return ResponseFormat.error(e.toString(), ResponseStatus.FAIL_NOT_FOUND.getStatusCode());
        } catch (RuntimeException e) {
            return ResponseFormat.error(e.toString(), ResponseStatus.FAIL_BAD_REQUEST.getStatusCode());
        }
    }

    @GetMapping("/search/specialties")
    public ResponseFormat<Set<String>> getVetSpecialties() {

        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, vetService.getVetSpecialties());
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    @PutMapping("/specialties")
    public ResponseFormat<Void> addSpecialtiesByVet(@RequestBody VetReqDTO.CONDITION condition) {

        try {
            vetService.addSpecialtiesByVet(condition);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    @DeleteMapping("/specialties")
    public ResponseFormat<Void> deleteSpecialtiesByVet(@RequestBody VetReqDTO.CONDITION condition) {

        try {
            vetService.deleteSpecialtiesByVet(condition);
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
