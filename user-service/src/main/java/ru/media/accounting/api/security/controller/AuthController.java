package ru.media.accounting.api.security.controller;

import ru.media.accounting.dto.UserRequest;
import ru.media.accounting.dto.UserResponse;
import ru.media.accounting.dto.auth.JwtRequest;
import ru.media.accounting.dto.auth.JwtResponse;

public interface AuthController {

    JwtResponse login(JwtRequest loginRequest);

    UserResponse register(UserRequest userRequest);

    JwtResponse refresh(String refreshToken);

}
