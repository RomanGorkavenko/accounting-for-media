package ru.media.accounting.api.security.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.media.accounting.api.mappers.UserMapper;
import ru.media.accounting.api.security.controller.AuthController;
import ru.media.accounting.api.security.service.AuthService;
import ru.media.accounting.dto.UserRequest;
import ru.media.accounting.dto.UserResponse;
import ru.media.accounting.dto.auth.JwtRequest;
import ru.media.accounting.dto.auth.JwtResponse;
import ru.media.accounting.service.UserService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth Controller", description = "Auth API")
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;

    @CrossOrigin(origins = "http://localhost:8765")
    @PostMapping("/login")
    public JwtResponse login(@RequestBody JwtRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRequest userRequest) {
        return userMapper.toDto(userService.save(userRequest));
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken) {
        return authService.refresh(refreshToken);
    }

}
