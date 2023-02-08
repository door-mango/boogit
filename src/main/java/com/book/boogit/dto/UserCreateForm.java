package com.book.boogit.dto;

import lombok.Getter;
import lombok.Setter;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Size;


@Getter
@Setter public class UserCreateForm {

//    @NotEmpty(message = "이메일은 필수항목입니다.")
//    @Email
    private String email;

    private String username;

    private String password1;

    private String password2;
}
