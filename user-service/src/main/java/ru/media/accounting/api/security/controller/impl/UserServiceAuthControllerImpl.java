package ru.media.accounting.api.security.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.media.accounting.api.mappers.UserMapper;
import ru.media.accounting.api.security.controller.UserServiceAuthController;
import ru.media.accounting.api.security.service.UserServiceAuthService;
import ru.media.accounting.dto.user.UserRequest;
import ru.media.accounting.dto.user.UserResponse;
import ru.media.accounting.dto.auth.JwtRequest;
import ru.media.accounting.dto.auth.JwtResponse;
import ru.media.accounting.service.UserService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth Controller", description = "Auth API")
public class UserServiceAuthControllerImpl implements UserServiceAuthController {

    private final UserServiceAuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;

    @CrossOrigin(origins = "http://localhost:8765")
    @PostMapping("/login")
    public JwtResponse login(@RequestBody JwtRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @CrossOrigin(origins = "http://localhost:8765")
    @PostMapping("/register")
    @PreAuthorize("@userServiceCustomSecurityExpression.canAccessUserROLE_ADMIN(#userRequest.username)")
    public UserResponse register(@RequestBody UserRequest userRequest) {
        return userMapper.toDto(userService.save(userRequest));
    }

    @CrossOrigin(origins = "http://localhost:8765")
    @PostMapping("/refresh")
    @PreAuthorize("@userServiceCustomSecurityExpression.canAccessUserROLE_ADMIN()")
    public JwtResponse refresh(@RequestBody String refreshToken) {
        return authService.refresh(refreshToken);
    }

}
