package com.book.boogit.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping(value = "/git")
public class GitController {

    @GetMapping("/register")
    public String register() {
        return "git/register";
    }

}
