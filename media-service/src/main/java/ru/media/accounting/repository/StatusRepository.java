package ru.media.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.media.accounting.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
