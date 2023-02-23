package com.book.boogit.dto;

import com.book.boogit.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {
    private String title;
    private String author;
    private String publisher;
    private Date publication_date;
    private int price;
    private String isbn;
    private int totalPageNo;
    private String thumbnail;
    private String url;
    private String status;
    private String finishYn;
    private String openYn;
    private String delYn;

    public static Book toEntity(final BookDTO dto) {
        return Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .publisher(dto.getPublisher())
                .publicationDate(dto.getPublication_date())
                .price(dto.getPrice())
                .isbn(dto.getIsbn())
                .totalPageNo(dto.getTotalPageNo())
                .thumbnail(dto.getThumbnail())
                .url(dto.getUrl())
                .build();
    }

}
