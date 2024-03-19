package ru.media.accounting.api.controller;

import org.springframework.http.ResponseEntity;
import ru.media.accounting.dto.UserRequest;
import ru.media.accounting.dto.UserResponse;

public interface UserController {

    ResponseEntity<UserResponse> findByUsername(String username);

    ResponseEntity<UserResponse> findByEmail(String email);

    ResponseEntity<UserResponse> save(UserRequest userRequest);
}
