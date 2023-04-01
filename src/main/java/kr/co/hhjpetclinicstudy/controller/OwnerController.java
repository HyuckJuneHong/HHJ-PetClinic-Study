package kr.co.hhjpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.hhjpetclinicstudy.service.OwnerService;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
}
