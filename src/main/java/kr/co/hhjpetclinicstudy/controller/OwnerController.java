package kr.co.hhjpetclinicstudy.controller;

import kr.co.hhjpetclinicstudy.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;
}
