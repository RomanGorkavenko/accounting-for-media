package ru.media.accounting.api.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.media.accounting.api.controller.UserController;
import ru.media.accounting.api.controller.mappers.UserMapper;
import ru.media.accounting.api.dto.UserRequest;
import ru.media.accounting.api.dto.UserResponse;
import ru.media.accounting.model.User;
import ru.media.accounting.service.UserService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    @GetMapping("/user/{username}")
    public ResponseEntity<UserResponse> findByUsername(@PathVariable("username") String username) {
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @Override
    public ResponseEntity<UserResponse> findByEmail(String email) {
        return null;
    }

    @Override
    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody UserRequest userRequest) {
        User user = userService.save(userRequest);
        return ResponseEntity.ok(userMapper.toDto(user));
    }
}
