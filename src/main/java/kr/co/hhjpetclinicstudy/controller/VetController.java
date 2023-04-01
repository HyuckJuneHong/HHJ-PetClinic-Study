package kr.co.hhjpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.hhjpetclinicstudy.service.VetService;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VetReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vets")
@RequiredArgsConstructor
public class VetController {
    private final VetService vetService;

    @PostMapping
    public ResponseEntity<String> createVet(@RequestBody @Valid VetReqDTO.CREATE create){
        try {
            vetService.createVet(create);
            return ResponseEntity.ok().body("Success Vet Create");
        }catch (Exception e){
            return ResponseEntity.ok().body("error : " + e);
        }
    }
}
