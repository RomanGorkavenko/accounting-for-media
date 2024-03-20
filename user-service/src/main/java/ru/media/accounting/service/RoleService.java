package ru.media.accounting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.media.accounting.model.Role;
import ru.media.accounting.repository.RoleRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    /**
     * Возвращает роль по названию.
     * @param title название роли.
     * @return {@link Role} роль.
     */
    public Role getRole(String title) {
        return roleRepository.findByTitle(title)
                .orElseThrow(() -> new NoSuchElementException("Роль role = " + title + " не найдена"));
    }
}
