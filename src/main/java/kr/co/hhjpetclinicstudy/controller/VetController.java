package kr.co.hhjpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.hhjpetclinicstudy.service.VetService;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VetReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.VetResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        } catch (Exception e){
            return ResponseEntity.ok().body("error : " + e);
        }
    }

    @GetMapping("/{vet_id}")
    public ResponseEntity<VetResDTO.READ> getVetById(@PathVariable(name = "vet_id") Long vetId) throws Exception {

        try {
            return ResponseEntity.ok().body(vetService.getVetById(vetId));
        } catch (Exception e){
            throw new Exception("error : " + e);
        }
    }

    @PutMapping
    public ResponseEntity<String> updateVet(@RequestBody VetReqDTO.UPDATE update){

        try {
            vetService.updateVet(update);
            return ResponseEntity.ok().body("Success Vet Update");
        } catch (Exception e){
            return ResponseEntity.ok().body("error : "+ e);
        }
    }

    @DeleteMapping("/{vet_id}")
    public ResponseEntity<String> deleteVetById(@PathVariable(name = "vet_id") Long vetId){

        try {
            vetService.deleteVetById(vetId);
            return ResponseEntity.ok().body("Success Delete Vet");
        } catch (Exception e){
            return ResponseEntity.ok().body("error : " + e);
        }
    }
}
