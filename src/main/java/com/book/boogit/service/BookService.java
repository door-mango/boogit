package com.book.boogit.service;

import com.book.boogit.entity.Book;
import com.book.boogit.entity.User;
import com.book.boogit.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class BookService {

    private final BookRepository bookRepository;

    public Book create(final Book book) {
        if(book == null) {
            throw new RuntimeException("Invalid Arguments");
        }

        log.info("BookService create");
        log.info("createBook req Object : {}", book);

        // 추후에 isbn으로 중복 검색해서 거르기
        /*final String email = book.getEmail();
        if(userRepository.existsByEmail(email)) {
            log.warn("Email already exists {}", email);
            throw new RuntimeException("Email Already Exists");
        }*/

        return bookRepository.save(book);

    }
}
