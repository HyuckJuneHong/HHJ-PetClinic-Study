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
    @GetMapping("/{owner_id}")
    public ResponseEntity<List<PetResDTO.READ>> getPetsByOwner(@PathVariable(name = "owner_id") Long ownerId) throws Exception {

        try {
            return ResponseEntity.ok().body(petService.getPetsByOwner(ownerId));
        }catch (Exception e){
            throw new Exception("error : " + e);
        }
    }

    /**
     * Pet Update API
     * @param update : Info for Update a Pet
     * @return : String
0     */
    @PutMapping
    public ResponseEntity<String> updatePet(@RequestBody @Valid PetReqDTO.UPDATE update){

        try {
            petService.updatePet(update);
            return ResponseEntity.ok().body("Success Pet Update");
        }catch (Exception e){
            return ResponseEntity.ok().body("error : " + e);
        }
    }

    /**
     * Pet Delete API
     * @param petId : id for Delete a Pet
     * @return : String
     */
    @DeleteMapping("/{pet_id}")
    public ResponseEntity<String> deletePetById(@PathVariable(name = "pet_id") Long petId){

        try {
            petService.deletePetById(petId);
            return ResponseEntity.ok().body("Success Pet Delete");
        }catch (Exception e){
            return ResponseEntity.ok().body("error : " + e);
        }
    }
}
