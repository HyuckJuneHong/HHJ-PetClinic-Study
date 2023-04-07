package kr.co.hhjpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.hhjpetclinicstudy.infrastructure.error.exception.DuplicatedException;
import kr.co.hhjpetclinicstudy.infrastructure.error.exception.InvalidRequestException;
import kr.co.hhjpetclinicstudy.infrastructure.error.exception.NotFountException;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseFormat;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
import kr.co.hhjpetclinicstudy.service.service.OwnerService;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.OwnerResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    /**
     * owner create api
     * @param create : Info for create an owner
     */
    @PostMapping
    public ResponseFormat<Void> createOwner(@RequestBody @Validated OwnerReqDTO.CREATE create){

        try {
            ownerService.createOwner(create);
            return ResponseFormat.success(ResponseStatus.SUCCESS_CREATE);
        } catch (DuplicatedException e){
            return ResponseFormat.error(ResponseStatus.FAIL_TELEPHONE_DUPLICATED);
        } catch (RuntimeException e){
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * owner get api
     * @param ownerId : id for get an owner
     * @return : OwnerResDTO.READ
     */
    @GetMapping("/{owner_id}")
    public ResponseFormat<OwnerResDTO.READ> getOwnerById(@PathVariable(name = "owner_id") Long ownerId) {

        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, ownerService.getOwnerById(ownerId));
        } catch (NotFountException e){
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e){
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * owner update api
     * @param update : info for update an owner
     */
    @PutMapping
    public ResponseFormat<Void> updateOwner(@RequestBody @Valid OwnerReqDTO.UPDATE update){

        try {
            ownerService.updateOwner(update);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (DuplicatedException e){
            return ResponseFormat.error(ResponseStatus.FAIL_TELEPHONE_DUPLICATED);
        } catch (NotFountException e){
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e){
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * owner delete api
     * @param ownerId : id for delete on owner
     */
    @DeleteMapping("/{owner_id}")
    public ResponseFormat<Void> deleteOwnerById(@PathVariable(name = "owner_id") Long ownerId){

        try {
            ownerService.deleteOwnerById(ownerId);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFountException e){
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e){
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }
}
