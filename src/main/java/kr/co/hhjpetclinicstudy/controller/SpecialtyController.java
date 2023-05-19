package kr.co.hhjpetclinicstudy.controller;

import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseFormat;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.SpecialtyReqDTO;
import kr.co.hhjpetclinicstudy.service.service.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/specialties")
@RequiredArgsConstructor
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    @GetMapping
    public ResponseFormat<Set<String>> getExistSpecialties() {

        return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, specialtyService.getExistSpecialties());
    }

    @PutMapping("/vets/{vet_id}")
    public ResponseFormat<Void> addSpecialtiesByVet(@PathVariable(name = "vet_id") Long vetId,
                                                    @RequestBody SpecialtyReqDTO.UPDATE update) {

        specialtyService.addSpecialtiesByVet(vetId, update);
        return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
    }

    @DeleteMapping("/vets/{vet_id}")
    public ResponseFormat<Void> deleteSpecialtiesByVet(@PathVariable(name = "vet_id") Long vetId,
                                                       @RequestBody SpecialtyReqDTO.UPDATE update) {

        specialtyService.deleteSpecialtiesByVet(vetId, update);
        return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
    }
}
