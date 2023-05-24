package kr.co.hhjpetclinicstudy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admins")
@RequiredArgsConstructor
public class AdminController {

    @GetMapping("/hello")
    public String hello(){

        return "Hello World";
    }

    @PostMapping("/login")
    public String loginPage(){

        return "login-page";
    }
}
