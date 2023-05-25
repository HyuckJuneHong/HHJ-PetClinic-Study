package kr.co.hhjpetclinicstudy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    @GetMapping
    public String main() {

        return "main-page";
    }

    @GetMapping("/hello")
    public String hello() {

        return "Hello World";
    }

    @GetMapping("/fail")
    public String fail() {

        return "login-fail";
    }
}