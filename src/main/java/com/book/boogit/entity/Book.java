package com.book.boogit.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Book {
    //https://velog.io/@deannn/Spring-Boot-Blog-Project-DB-%ED%85%8C%EC%9D%B4%EB%B8%94-%EB%A7%8C%EB%93%A4%EA%B8%B0
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User user;
    @Column(nullable = false, length = 300)
    private String title;
    @Column(nullable = false, length = 300)
    private String author;
    @Column(nullable = false, length = 300)
    private String publisher;
    @Column(name="publication_date")
    private Date publicationDate;
    private int price;
    private String isbn;
    private int totalPageNo;
    private String thumbnail;
    private String url;
    @ColumnDefault("'before'")
    private String status;
    @Column(name="finish_yn")
    @ColumnDefault("'N'")
    private String finishYn;
    @Column(name="open_yn")
    @ColumnDefault("'Y'")
    private String openYn;
    @Column(name="del_yn")
    @ColumnDefault("'N'")
    private String delYn;
    @CreatedDate
    @Column(name="created_date")
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(name="modified_date")
    private LocalDateTime modifiedDate;


}
