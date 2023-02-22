package com.book.boogit.dto;

import com.book.boogit.entity.Role;
import com.book.boogit.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String token;
    private String email;
    private String password;
    private String username;
    private Long id;
//    private Role role;

    /* DTO -> Entity */
//    public User toEntity() {
//        User user = User.builder()
//                .email(email)
//                .password(password)
//                .username(username)
//                .role(Role.USER)
//                .build();
//        return user;
//    }
}
