package com.book.boogit.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="user", schema = "boogit", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @CreatedDate
    @Column(name="created_date")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name="modified_date")
    private LocalDateTime modifiedDate;

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Role role;

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }






}
