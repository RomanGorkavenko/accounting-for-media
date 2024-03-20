package ru.media.accounting.api.security.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.media.accounting.dto.UserRequest;
import ru.media.accounting.dto.UserResponse;
import ru.media.accounting.dto.auth.JwtRequest;
import ru.media.accounting.dto.auth.JwtResponse;

public interface AuthController {

    JwtResponse login(JwtRequest loginRequest);

    UserResponse register(UserRequest userRequest);

    JwtResponse refresh(String refreshToken);

}
