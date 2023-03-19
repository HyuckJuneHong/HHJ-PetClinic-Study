package kr.co.hhjpetclinicstudy.controller;

import kr.co.hhjpetclinicstudy.service.OwnersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OwnersController {

    private final OwnersService ownersService;
}
