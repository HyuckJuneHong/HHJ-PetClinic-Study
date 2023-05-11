package kr.co.hhjpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.hhjpetclinicstudy.infrastructure.error.exception.DuplicatedException;
import kr.co.hhjpetclinicstudy.infrastructure.error.exception.NotFoundException;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseFormat;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.IdsReqDTO;
import kr.co.hhjpetclinicstudy.service.service.OwnerService;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.OwnerResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    /**
     * owner create api
     *
     * @param create : Info for create an owner
     */
    @PostMapping
    public ResponseFormat<Void> createOwner(@RequestBody @Validated OwnerReqDTO.CREATE create) {

        try {
            ownerService.createOwner(create);
            return ResponseFormat.success(ResponseStatus.SUCCESS_CREATE);
        } catch (DuplicatedException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_TELEPHONE_DUPLICATED);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    @PostMapping("/search")
    public ResponseFormat<List<OwnerResDTO.READ>> getOwnersByIds(@RequestBody IdsReqDTO ownerIds) {

        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, ownerService.getOwnersByIds(ownerIds));
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * owner update api
     *
     * @param update : info for update an owner
     */
    @PutMapping("/{owner_id}")
    public ResponseFormat<Void> updateOwner(@PathVariable(name = "owner_id") Long ownerId,
                                            @RequestBody @Valid OwnerReqDTO.UPDATE update) {

        try {
            ownerService.updateOwner(ownerId, update);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (DuplicatedException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_TELEPHONE_DUPLICATED);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseFormat<Void> deleteOwnersByIds(@RequestBody IdsReqDTO ownerIds) {

        try {
            ownerService.deleteOwnersByIds(ownerIds);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }
}
