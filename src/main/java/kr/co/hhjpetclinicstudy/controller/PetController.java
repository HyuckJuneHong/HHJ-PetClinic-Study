package kr.co.hhjpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.hhjpetclinicstudy.service.PetService;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.PetReqDTO;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pets")
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;

    /**
     * Pet Create API
     * @param create : Info for Create a Pet
     * @return : String
     */
    public ResponseEntity<String> createPet(@RequestBody @Valid PetReqDTO.CREATE create){
        try{
            petService.createPet(create);
            return ResponseEntity.ok().body("Success Pet Create");
        }catch (Exception e){
            return ResponseEntity.ok().body("error :" + e.toString());
        }
    }
}
