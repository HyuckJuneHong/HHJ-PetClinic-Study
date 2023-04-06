package kr.co.hhjpetclinicstudy.controller;

import kr.co.hhjpetclinicstudy.service.model.dtos.request.VisitReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.VisitResDTO;
import kr.co.hhjpetclinicstudy.service.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visits")
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;

    /**
     * Visit Create API
     * @param create : info for Create a visit
     * @return : String
     */
    @PostMapping
    public ResponseEntity<String> createVisit(VisitReqDTO.CREATE create){

        try {
            visitService.createVisit(create);
            return ResponseEntity.ok().body("Success create visit");
        }catch (Exception e){
            return ResponseEntity.ok().body("error : " + e);
        }
    }

    /**
     * Visit By Pet Read API
     * @return : List VisitResDTO.READ
     */
    @GetMapping("/{pet_id}")
    public ResponseEntity<List<VisitResDTO.READ>> getVisitsByPet(@PathVariable(name = "pet_id") Long petId) throws Exception{

        try {
            return ResponseEntity.ok().body(visitService.getVisitsByPet(petId));
        }catch (Exception e){
            throw new Exception("error : " + e);
        }
    }

    /**
     * Visit By id Read API
     * @return : VisitResDTO.READ
     */
    @GetMapping("/{pet_id}/{visit_id}")
    public ResponseEntity<VisitResDTO.READ> getVisitById(@PathVariable(name = "visit_id") Long visitId,
                                                         @PathVariable(name = "pet_id") Long petId) throws Exception{

        try {
            return ResponseEntity.ok().body(visitService.getVisitById(visitId));
        }catch (Exception e){
            throw new Exception("error : " + e);
        }
    }

}
