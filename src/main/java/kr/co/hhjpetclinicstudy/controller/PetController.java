package kr.co.hhjpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.hhjpetclinicstudy.infrastructure.error.exception.NotFoundException;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseFormat;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
import kr.co.hhjpetclinicstudy.service.service.PetService;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.PetReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.PetResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    /**
     * Pet Create API
     *
     * @param create : Info for Create a Pet
     */
    @PostMapping("/{owner_id}")
    public ResponseFormat<Void> createPet(@PathVariable(name = "owner_id") Long ownerId,
                                          @RequestBody @Valid PetReqDTO.CREATE create) {

        try {
            petService.createPet(ownerId, create);
            return ResponseFormat.success(ResponseStatus.SUCCESS_CREATE);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    @GetMapping("/{pet_id}")
    public ResponseFormat<PetResDTO.READ_DETAIL> getPetById(@PathVariable(name = "pet_id") Long petId) {

        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, petService.getPetById(petId));
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    @GetMapping("/owners/{owner_id}")
    public ResponseFormat<List<PetResDTO.READ>> getPetsByOwner(@PathVariable(name = "owner_id") Long ownerId) {

        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, petService.getPetsByOwner(ownerId));
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * Pet Update API
     *
     * @param update : Info for Update a Pet
     */
    @PutMapping("/{pet_id}")
    public ResponseFormat<Void> updatePet(@PathVariable(name = "pet_id") Long petId,
                                          @RequestBody @Valid PetReqDTO.UPDATE update) {

        try {
            petService.updatePet(petId, update);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * Pet Delete API
     *
     * @param petId : id for Delete a Pet
     */
    @DeleteMapping("/{pet_id}")
    public ResponseFormat<Void> deletePetById(@PathVariable(name = "pet_id") Long petId) {

        try {
            petService.deletePetById(petId);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }
}
