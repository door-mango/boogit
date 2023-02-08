package com.book.boogit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String main() {
        return "login";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/join")
    public String moveToJoin() {
        return "join";
    }

    @GetMapping("/login")
    public String moveToLogin() {
        return "login";
    }

    @GetMapping("/detail")
    public String moveToDetail() {
        return "detail";
    }


}