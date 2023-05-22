package kr.co.hhjpetclinicstudy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/")
    public String index(){

        return "Hello World";
    }

    @GetMapping("login-page")
    public String loginPage(){

        return "login-page";
    }
}
