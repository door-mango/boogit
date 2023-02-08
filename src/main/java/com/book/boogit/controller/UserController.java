package com.book.boogit.controller;

import java.util.Optional;

import com.book.boogit.dto.ResponseDTO;
import com.book.boogit.dto.UserDTO;
import com.book.boogit.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.book.boogit.entity.User;
import com.book.boogit.repository.UserRepository;
import com.book.boogit.service.UserService;
import org.springframework.web.servlet.ModelAndView;


// [참고 url]  https://cjw-awdsd.tistory.com/24
// [참고 url]  https://github.com/semihumanbeing


@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping(value = "/user") // @RequestMapping("/auth")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final TokenProvider tokenProvider;
    private  final PasswordEncoder encoder;

    // 회원가입
    @PostMapping("/join") // @PostMapping("/signup")
    public ResponseEntity<?> registerUser(UserDTO userDTO) {
        try {
            // 요청에 따라 회원 생성
            User user = User.builder()
                    .email(userDTO.getEmail())
                    .username(userDTO.getUsername())
                    .password(encoder.encode(userDTO.getPassword()))
                    .build();
            // 서비스를 통해 레포지토리 저장 로직 실행
            User registeredUser = userService.create(user);
            UserDTO responseUserDTO = UserDTO.builder()
                    .email(registeredUser.getEmail())
                    .id(registeredUser.getId())
                    .username(registeredUser.getUsername())
                    .build();

            return ResponseEntity.ok().body(responseUserDTO);

        } catch (Exception e) {
            // ResponseDTO에 에러 메세지 담아서 전달
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    // 회원 로그인
    @PostMapping("/login") // @PostMapping("/signin")
//    public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO) {
    public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO) {

        User user = userService.getByCredentials(
                userDTO.getEmail(),
                userDTO.getPassword(),
                encoder);

        if(user != null) {
            // 토큰 생성
            final String token = tokenProvider.create(user);
            final UserDTO responseUserDTO = UserDTO.builder()
                    .email(user.getEmail())
                    .id(user.getId())
                    .username(user.getUsername())
                    .token(token)
                    .build();

            return ResponseEntity.ok().body(responseUserDTO);
        } else {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .error("Login Failed")
                    .build();

            return ResponseEntity.badRequest().body(responseDTO);
        }
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



}