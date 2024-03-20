package ru.media.accounting.api.security.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.media.accounting.api.feign.UserFeignClient;
import ru.media.accounting.api.security.controller.AuthController;
import ru.media.accounting.dto.UserRequest;
import ru.media.accounting.dto.UserResponse;
import ru.media.accounting.dto.auth.JwtRequest;
import ru.media.accounting.dto.auth.JwtResponse;
import ru.media.accounting.api.security.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth Controller", description = "Auth API")
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;
    private final UserFeignClient userFeignClient;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody JwtRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRequest userRequest) {
        return userFeignClient.save(userRequest).getBody();
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken) {
        return authService.refresh(refreshToken);
    }

}
