package ru.media.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.media.accounting.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByTitle(String title);
}
