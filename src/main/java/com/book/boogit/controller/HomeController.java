package com.book.boogit.controller;

import com.book.boogit.config.auth.LoginUser;
import com.book.boogit.dto.UserSessionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String main() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/index")
    public String index(@LoginUser UserSessionDTO user) {
        if(user != null) {
            log.info("[로그인 회원] {}", user.getId());
        }
        return "index";
    }

    @GetMapping("/login")
    public String moveToLogin() {
        return "login";
    }

}