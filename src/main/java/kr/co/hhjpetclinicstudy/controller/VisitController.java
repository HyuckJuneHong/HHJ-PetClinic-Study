package kr.co.hhjpetclinicstudy.controller;

import kr.co.hhjpetclinicstudy.service.model.dtos.request.VisitReqDTO;
import kr.co.hhjpetclinicstudy.service.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/visits")
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;

    @PostMapping
    public ResponseEntity<String> createVisit(VisitReqDTO.CREATE create){

        try {
            visitService.createVisit(create);
            return ResponseEntity.ok().body("Success create visit");
        }catch (Exception e){
            return ResponseEntity.ok().body("error : " + e);
        }
    }
}
