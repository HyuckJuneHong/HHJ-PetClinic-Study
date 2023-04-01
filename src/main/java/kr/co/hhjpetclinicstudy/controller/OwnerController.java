package kr.co.hhjpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.hhjpetclinicstudy.service.OwnerService;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.OwnerResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    /**
     * owner create api
     * @param create : Info for create a owner
     * @return : String
     */
    public ResponseEntity<String> createOwner(@RequestBody @Valid OwnerReqDTO.CREATE create){
        try {
            ownerService.createOwner(create);
            return ResponseEntity.ok().body("Success Owner Create");
        }catch (Exception e){
            return ResponseEntity.ok().body("error : " + e);
        }
    }

    /**
     * owner get api
     * @param ownerId : id for get a owner
     * @return : OwnerResDTO.READ
     */
    public ResponseEntity<OwnerResDTO.READ> getOwnerById(@PathVariable(name = "owner_id") Long ownerId) throws Exception {
        try {
            return ResponseEntity.ok().body(ownerService.getOwnerById(ownerId));
        }catch (Exception e){
            throw new Exception("error : " + e);
        }
    }
}
