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

        vetService.createVetAndSpecialties(create);
        return ResponseFormat.success(ResponseStatus.SUCCESS_CREATE);
    }

    @GetMapping("/{vet_id}")
    public ResponseFormat<VetResDTO.READ> getVetsById(@PathVariable(name = "vet_id") Long vetId) {

        return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, vetService.getVetsById(vetId));
    }

    @GetMapping("/specialties")
    public ResponseFormat<Set<String>> getExistSpecialties() {

        return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, vetService.getExistSpecialties());
    }

    @PutMapping("/specialties/{vet_id}")
    public ResponseFormat<Void> addSpecialtiesByVet(@PathVariable(name = "vet_id") Long vetId,
                                                    @RequestBody SpecialtyReqDTO.UPDATE update) {

        vetService.addSpecialtiesByVet(vetId, update);
        return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
    }

    @DeleteMapping("/specialties/{vet_id}")
    public ResponseFormat<Void> deleteSpecialtiesByVet(@PathVariable(name = "vet_id") Long vetId,
                                                       @RequestBody SpecialtyReqDTO.UPDATE update) {

        vetService.deleteSpecialtiesByVet(vetId, update);
        return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
    }

    @DeleteMapping
    public ResponseFormat<Void> deleteVetsByIds(@RequestBody VetReqDTO.CONDITION condition) {

        vetService.deleteVetsByIds(condition);
        return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
    }
}
