package ru.media.accounting.dto.user;

import lombok.Data;
import ru.media.accounting.model.Role;

import java.util.Set;

@Data
public class UserResponse {

    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private Set<Role> roles;
}
