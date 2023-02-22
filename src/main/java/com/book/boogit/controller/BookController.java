package com.book.boogit.controller;

import com.book.boogit.config.LoginUser;
import com.book.boogit.dto.BookDTO;
import com.book.boogit.dto.ResponseDTO;
import com.book.boogit.dto.UserDTO;
import com.book.boogit.dto.UserSessionDTO;
import com.book.boogit.entity.Book;
import com.book.boogit.repository.BookRepository;
import com.book.boogit.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping(value = "/book")
public class BookController {

    private final BookRepository bookRepository;
    private final BookService bookService;

    @GetMapping("/register")
    public String register() {
        return "book/register";
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerBook(@RequestBody BookDTO bookDTO) {
        try {
//            if(user != null) {
//                log.info("[로그인 회원] {}", user);
//            }
            Book book = BookDTO.toEntity(bookDTO);
            log.info("[등록 대상] {} ", book);

            // 서비스를 통해 레포지토리 저장 로직 실행
            Book registeredBook = bookService.create(book);
            BookDTO responseBookDTO = BookDTO.builder()
                    .title(registeredBook.getTitle())
                    .author(registeredBook.getAuthor())
                    .publisher(registeredBook.getPublisher())
                    .publication_date(registeredBook.getPublicationDate())
                    .price(registeredBook.getPrice())
                    .isbn(registeredBook.getIsbn())
                    .totalPageNo(registeredBook.getTotalPageNo())
                    .thumbnail(registeredBook.getThumbnail())
                    .url(registeredBook.getUrl())
                    .build();

            return ResponseEntity.ok().body(responseBookDTO);

        } catch (Exception e) {
            // ResponseDTO에 에러 메세지 담아서 전달
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

}
