package ru.media.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.media.accounting.model.Objects;

public interface ObjectsRepository extends JpaRepository<Objects, Long> {
}
