package kr.co.hhjpetclinicstudy.controller;

import kr.co.hhjpetclinicstudy.service.VetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RequiredArgsConstructor
public class VetsController {
    private final VetsService vetsService;
}
