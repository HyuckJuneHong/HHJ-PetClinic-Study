package kr.co.hhjpetclinicstudy.controller;

import kr.co.hhjpetclinicstudy.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;
}
