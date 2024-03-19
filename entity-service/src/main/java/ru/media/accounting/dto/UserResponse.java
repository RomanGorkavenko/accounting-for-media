package ru.media.accounting.dto;

import lombok.Data;

@Data
public class UserResponse {

    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
}
