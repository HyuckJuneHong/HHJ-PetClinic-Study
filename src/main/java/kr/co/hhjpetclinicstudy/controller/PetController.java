package kr.co.hhjpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.hhjpetclinicstudy.service.PetService;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.PetReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.PetResDTO;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    @PostMapping
    public ResponseEntity<String> createPet(@RequestBody @Valid PetReqDTO.CREATE create){
        try{
            petService.createPet(create);
            return ResponseEntity.ok().body("Success Pet Create");
        }catch (Exception e){
            return ResponseEntity.ok().body("error : " + e);
        }
    }

    /**
     * Pet Read API
     * @return : PetResDtp.READ
     */
    @GetMapping
    public ResponseEntity<List<PetResDTO.READ>> getPetsByAll() throws Exception {
        try {
            return ResponseEntity.ok().body(petService.getPetsByAll());
        }catch (Exception e){
            throw new Exception("error : " + e);
        }
    }
}
