package com.book.boogit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.boogit.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

    //https://parkhyeokjin.github.io/spring/2019/11/13/Spring-login.html
    //https://sundries-in-myidea.tistory.com/91
    //List<User> findByU_eml_id(String u_eml_id);



}