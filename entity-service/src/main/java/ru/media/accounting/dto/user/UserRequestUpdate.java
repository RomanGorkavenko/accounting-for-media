package ru.media.accounting.dto.user;

import lombok.Data;

@Data
public class UserRequestUpdate {

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;

}
