package ru.media.accounting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.media.accounting.dto.user.UserRequest;
import ru.media.accounting.dto.user.UserRequestUpdate;
import ru.media.accounting.exception.ElementAlreadyExistsException;
import ru.media.accounting.model.Role;
import ru.media.accounting.model.User;
import ru.media.accounting.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Поиск пользователя по username.
     * @param username Имя пользователя.
     * @return {@link User} пользователя.
     * @throws NoSuchElementException если пользователь не найден.
     */
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("Пользователь с username = " + username + " не найден"));
    }

    /**
     * Поиск пользователя по email.
     * @param email Email пользователя.
     * @return {@link User} пользователя.
     * @throws NoSuchElementException если пользователь не найден.
     */
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("Пользователь с email = " + email + " не найден"));
    }

    /**
     * Создание пользователя с ролью USER.
     * @param userRequest dto для создания пользователя.
     * @return {@link User} пользователя.
     */
    public User save(UserRequest userRequest) {
        if(userRepository.findByUsername(userRequest.getUsername()).isPresent() ||
        userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new ElementAlreadyExistsException(
                    "Пользователь уже существует");
        }

        User user = new User(userRequest.getUsername(), userRequest.getEmail(),
                passwordEncoder.encode(userRequest.getPassword()));
        Role role = roleService.getRole("ROLE_USER");
        Set<Role> roles = Set.of(role);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    /**
     * Обновление пользователя.
     * @param userRequestUpdate dto для обновления пользователя.
     * @return {@link User} пользователя.
     * @throws NoSuchElementException если пользователь не найден.
     */
    public User update(UserRequestUpdate userRequestUpdate) {
        User user = findByUsername(userRequestUpdate.getUsername());
        user.setFirstname(userRequestUpdate.getFirstname());
        user.setLastname(userRequestUpdate.getLastname());
        user.setUsername(userRequestUpdate.getUsername());
        user.setEmail(userRequestUpdate.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestUpdate.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Удаление пользователя.
     * @param username Имя пользователя.
     * @throws NoSuchElementException если пользователь не найден.
     */
    public void delete(String username) {
        User user = findByUsername(username);
        userRepository.delete(user);
    }

    /**
     * Получение списка всех пользователей.
     * @return список пользователей.
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

}
