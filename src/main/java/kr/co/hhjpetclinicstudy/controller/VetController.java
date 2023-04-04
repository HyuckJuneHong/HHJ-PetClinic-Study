package kr.co.hhjpetclinicstudy.controller;

import kr.co.hhjpetclinicstudy.service.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VetController {
    private final VetService vetService;
}
