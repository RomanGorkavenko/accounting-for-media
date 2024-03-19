package ru.media.accounting.api.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.media.accounting.api.controller.UserController;
import ru.media.accounting.api.mappers.UserMapper;
import ru.media.accounting.dto.UserRequest;
import ru.media.accounting.dto.UserResponse;
import ru.media.accounting.model.User;
import ru.media.accounting.service.UserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> findByUsername(@PathVariable("username") String username) {
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @Override
    @GetMapping("/mail/{email}")
    public ResponseEntity<UserResponse> findByEmail(@PathVariable("email") String email) {
        User user = userService.findByEmail(email);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @Override
    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody UserRequest userRequest) {
        User user = userService.save(userRequest);
        return ResponseEntity.ok(userMapper.toDto(user));
    }
}
