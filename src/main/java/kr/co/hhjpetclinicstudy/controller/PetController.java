package kr.co.hhjpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.hhjpetclinicstudy.infrastructure.error.exception.NotFountException;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseFormat;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
import kr.co.hhjpetclinicstudy.service.service.PetService;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.PetReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.PetResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseFormat<Void> createPet(@RequestBody @Valid PetReqDTO.CREATE create){

        try{
            petService.createPet(create);
            return ResponseFormat.success(ResponseStatus.SUCCESS_CREATE);
        }catch (NotFountException e){
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        }
    }

    /**
     * Pet Read API
     * @return : PetResDtp.READ
     */
    @GetMapping("/{owner_id}")
    public ResponseFormat<List<PetResDTO.READ>> getPetsByOwner(@PathVariable(name = "owner_id") Long ownerId) {

        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, petService.getPetsByOwner(ownerId));
        }catch (NotFountException e){
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        }
    }

    /**
     * Pet Update API
     * @param update : Info for Update a Pet
     * @return : String
     */
    @PutMapping
    public ResponseFormat<Void> updatePet(@RequestBody @Valid PetReqDTO.UPDATE update){

        try {
            petService.updatePet(update);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        }catch (NotFountException e){
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        }
    }

    /**
     * Pet Delete API
     * @param petId : id for Delete a Pet
     * @return : String
     */
    @DeleteMapping("/{pet_id}")
    public ResponseFormat<Void> deletePetById(@PathVariable(name = "pet_id") Long petId){

        try {
            petService.deletePetById(petId);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        }catch (Exception e){
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        }
    }
}
