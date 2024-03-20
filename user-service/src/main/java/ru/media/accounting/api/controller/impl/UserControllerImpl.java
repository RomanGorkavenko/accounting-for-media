package ru.media.accounting.api.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.media.accounting.api.controller.UserController;
import ru.media.accounting.api.mappers.UserMapper;
import ru.media.accounting.dto.UserRequest;
import ru.media.accounting.dto.UserRequestUpdate;
import ru.media.accounting.dto.UserResponse;
import ru.media.accounting.model.User;
import ru.media.accounting.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "User API")
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    @CrossOrigin(origins = "http://localhost:8765")
    @GetMapping("/name/{username}")
    @PreAuthorize("@customSecurityExpression.canAccessUserByUsername(#username)")
    public ResponseEntity<UserResponse> findByUsername(@PathVariable("username") String username) {
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @Override
    @CrossOrigin(origins = "http://localhost:8765")
    @GetMapping("/mail/{email}")
    @PreAuthorize("@customSecurityExpression.canAccessUserByEmail(#email)")
    public ResponseEntity<UserResponse> findByEmail(@PathVariable("email") String email) {
        User user = userService.findByEmail(email);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @Override
    @CrossOrigin(origins = "http://localhost:8765")
    @PostMapping
    @PreAuthorize("@customSecurityExpression.canAccessUserROLE_ADMIN(#userRequest.username)")
    public ResponseEntity<UserResponse> save(@RequestBody UserRequest userRequest) {
        User user = userService.save(userRequest);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @Override
    @CrossOrigin(origins = "http://localhost:8765")
    @PutMapping
    @PreAuthorize("@customSecurityExpression.canAccessUserROLE_ADMIN(#userRequestUpdate.username)")
    public ResponseEntity<UserResponse> update(@RequestBody UserRequestUpdate userRequestUpdate) {
        User user = userService.update(userRequestUpdate);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @Override
    @CrossOrigin(origins = "http://localhost:8765")
    @DeleteMapping
    @PreAuthorize("@customSecurityExpression.canAccessUserROLE_ADMIN(#username)")
    public ResponseEntity<String> delete(String username) {
        userService.delete(username);
        return new ResponseEntity<>("Пользователь с username = \"" + username + "\" успешно удален.", HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = "http://localhost:8765")
    @GetMapping("/all")
    @PreAuthorize("@customSecurityExpression.canAccessUserROLE_ADMIN()")
    public List<UserResponse> findAll() {
        return userMapper.toDto(userService.findAll());
    }
}