package kr.co.hhjpetclinicstudy.controller;

import kr.co.hhjpetclinicstudy.service.PetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PetsController {
    private final PetsService petsService;
}
