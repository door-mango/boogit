package com.book.boogit.dto;

import com.book.boogit.entity.User;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class UserSessionDTO implements Serializable {
    // https://dev-coco.tistory.com/127
    private Long id;
    private String email;
    private String username;
    //private LocalDateTime modifiedDate;

    /* Entity -> DTO */
    public UserSessionDTO(UserDTO user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        //this.modifiedDate = user.getModifiedDate(); // 회원정보 수정 시 수정 날짜를 업데이트해주기 위해 modifiedDate 추가
    }

    /* DTO -> Entity */
    public static User toEntity(UserSessionDTO sessionDTO) {
        User user = User.builder()
                .id(sessionDTO.getId())
                .email(sessionDTO.getEmail())
                .username(sessionDTO.getUsername())
                .build();
        return user;
    }
}
