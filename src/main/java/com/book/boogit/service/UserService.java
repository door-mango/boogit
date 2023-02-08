package com.book.boogit.service;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.book.boogit.entity.User;
import com.book.boogit.repository.UserRepository;

@RequiredArgsConstructor    // final이 붙은 속성을 포함하는 생성자를 자동으로 생성하는 롬복 어노테이션
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private  final PasswordEncoder encoder;



    public User create(final User user) {
        if(user == null || user.getEmail() == null) {
            throw new RuntimeException("Invalid Arguments");
        }

        log.info("UserService create");
        log.info("createUser req Object : {}", user);

        final String email = user.getEmail();
        if(userRepository.existsByEmail(email)) {
            log.warn("Email already exists {}", email);
            throw new RuntimeException("Email Already Exists");
        }

        return userRepository.save(user);

    }

    public User getByCredentials(final String email, final String password, final PasswordEncoder encoder) {

        final User originalUser = userRepository.findByEmail(email);

        log.info("UserService login");
        log.info("originalUser req Object : {}", originalUser);

        if(originalUser != null && encoder.matches(password, originalUser.getPassword())) {
            return originalUser;
        }
        return null;
    }


    public List<User> findAll() {
        List<User> list = (List<User>) userRepository.findAll();
        for (User user : list) {
            System.out.println(user.toString());
        }
        return list;
    }

    public User findOne(Long id) {
        User user = userRepository.findById(id).get();
        System.out.println(user);
        return user;
    }


    public void deleteItem(Long id) {
        System.err.println("ItemService deleteItem");
        userRepository.deleteById(id);
    }


}
