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
    public ResponseFormat<Void> createPetByOwner(@PathVariable(name = "owner_id") Long ownerId,
                                                 @RequestBody @Valid PetReqDTO.CREATE create) {

        try {
            petService.createPetByOwner(ownerId, create);
            return ResponseFormat.success(ResponseStatus.SUCCESS_CREATE);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    @PostMapping("/search")
    public ResponseFormat<List<PetResDTO.READ_DETAIL>> getPetsByIds(@RequestBody PetReqDTO.CONDITION condition) {

        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, petService.getPetsByIds(condition));
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    @PostMapping("/search/owners")
    public ResponseFormat<List<PetResDTO.READ>> getPetsByOwner(@RequestBody PetReqDTO.CONDITION condition) {

        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, petService.getPetsByOwner(condition));
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    @PutMapping("/{pet_id}")
    public ResponseFormat<Void> updatePetById(@PathVariable(name = "pet_id") Long petId,
                                              @RequestBody @Valid PetReqDTO.UPDATE update) {

        try {
            petService.updatePetById(petId, update);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseFormat<Void> deletePetsByIds(@RequestBody PetReqDTO.CONDITION condition) {

        try {
            petService.deletePetsByIds(condition);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }
}
