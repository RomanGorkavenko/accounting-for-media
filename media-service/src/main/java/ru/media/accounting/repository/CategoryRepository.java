package ru.media.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.media.accounting.model.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByTitle(String title);
}
