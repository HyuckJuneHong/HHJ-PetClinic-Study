package kr.co.hhjpetclinicstudy.controller;

import kr.co.hhjpetclinicstudy.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VisitController {
    private final VisitService visitService;
}