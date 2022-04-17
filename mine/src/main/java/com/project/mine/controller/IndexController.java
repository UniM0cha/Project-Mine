package com.project.mine.controller;

import java.security.Principal;

import com.project.mine.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class IndexController {

    private final UserService userService;

    @GetMapping("/")
    public String home(Principal principal) {
        String userId = principal.getName();
        log.info("========= principal.getName() ======> " + principal.getName());
        String email = userService.getEmail(userId);
        log.info("========== email =========> " + email);
        return "index";
    }
}
