package ru.media.accounting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.media.accounting.dto.UserRequest;
import ru.media.accounting.dto.UserRequestUpdate;
import ru.media.accounting.model.Role;
import ru.media.accounting.model.User;
import ru.media.accounting.repository.RoleRepository;
import ru.media.accounting.repository.UserRepository;

import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("Пользователь с username = " + username + " не найден"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("Пользователь с email = " + email + " не найден"));
    }

    public User save(UserRequest userRequest) {
        User user = new User(userRequest.getUsername(), userRequest.getEmail(), userRequest.getPassword());
        Role role = roleRepository.findByTitle("ROLE_USER");
        Set<Role> roles = Set.of(role);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public User update(UserRequestUpdate userRequestUpdate) {
        User user = findByUsername(userRequestUpdate.getUsername());
        user.setFirstname(userRequestUpdate.getFirstname());
        user.setLastname(userRequestUpdate.getLastname());
        user.setUsername(userRequestUpdate.getUsername());
        user.setEmail(userRequestUpdate.getEmail());
        return userRepository.save(user);
    }



}
