package ru.media.accounting.dto.user;

import lombok.Data;

@Data
public class UserRequest {

    private String username;
    private String email;
    private String password;

}
