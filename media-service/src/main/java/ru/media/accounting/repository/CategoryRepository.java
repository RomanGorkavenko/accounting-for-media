package ru.media.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.media.accounting.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
