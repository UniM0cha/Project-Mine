package com.project.mine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @GetMapping("/login")
    public String loginPage() {
        log.info("=========> login page");
        return "/auth/login";
    }

    // @GetMapping("/logout")
    // public String logoutPage() {
    // log.info("=========> logout page");
    // return "/auth/logout";
    // }

    // @GetMapping("/login/kakao")
    // public String kakoRedirect(Model model) {
    // log.info("hello2");
    // return "index";
    // }
}
