package ru.media.accounting.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import ru.media.accounting.api.dto.UserRequest;
import ru.media.accounting.api.dto.UserResponse;

public interface UserController {

    ResponseEntity<UserResponse> findByUsername(String username);

    ResponseEntity<UserResponse> findByEmail(String email);

    ResponseEntity<UserResponse> save(UserRequest userRequest);
}
