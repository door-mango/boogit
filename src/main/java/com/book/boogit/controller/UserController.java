package com.book.boogit.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.boogit.entity.User;
import com.book.boogit.repository.UserRepository;
import com.book.boogit.service.UserService;


// [참고 url]  https://cjw-awdsd.tistory.com/24

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 회원 리스트 조회
    @GetMapping(produces = "application/json; charset=utf-8")
    public Iterable<User> list() {
        return userRepository.findAll();
    }

    // 회원 정보 조회
    @GetMapping(value = "/{id}", produces = "application/json; charset=utf-8")
    public Optional<User> findOne(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    // 회원가입
    @PostMapping("/join")
    public String join(User user) {
        userService.createUser(user);
        return "index";
    }


}