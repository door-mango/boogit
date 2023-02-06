package com.book.boogit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.boogit.entity.User;
import com.book.boogit.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

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

    public User createUser(User user) {
        System.err.println("UserService createUser");
        System.out.println(user.toString());
        User res = userRepository.save(user);
        System.out.println(res.toString());
        return res;
    }


}
