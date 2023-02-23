package com.book.boogit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.boogit.entity.User;


public interface UserRepository extends JpaRepository<User, Long>{

    User findByEmail(String email);

    boolean existsByEmail(String email);

    //https://parkhyeokjin.github.io/spring/2019/11/13/Spring-login.html
    //https://sundries-in-myidea.tistory.com/91


}