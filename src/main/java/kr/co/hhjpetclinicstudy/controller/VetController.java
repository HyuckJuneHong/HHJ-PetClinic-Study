package kr.co.hhjpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseFormat;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VetReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VetSpecialtyReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.VetResDTO;
import kr.co.hhjpetclinicstudy.service.service.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vets")
@RequiredArgsConstructor
public class VetController {

    private final VetService vetService;

    @PostMapping
    public ResponseFormat<Void> createVetAndSpecialties(@RequestBody @Valid VetSpecialtyReqDTO.CREATE create) {

        vetService.createVetAndSpecialties(create);
        return ResponseFormat.success(ResponseStatus.SUCCESS_CREATE);
    }

    @GetMapping("/{vet_id}")
    public ResponseFormat<VetResDTO.READ> getVetById(@PathVariable(name = "vet_id") Long vetId) {

        return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, vetService.getVetById(vetId));
    }

    @DeleteMapping
    public ResponseFormat<Void> deleteVetsByIds(@RequestBody VetReqDTO.CONDITION condition) {

        vetService.deleteVetsByIds(condition);
        return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
    }
}
