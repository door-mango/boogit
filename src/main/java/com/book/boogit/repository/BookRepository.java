package com.book.boogit.repository;

import com.book.boogit.entity.Book;
import com.book.boogit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long>{

    List<Book> findByUser(User user);
}