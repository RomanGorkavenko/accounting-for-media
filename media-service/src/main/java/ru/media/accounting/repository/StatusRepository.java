package ru.media.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.media.accounting.model.Status;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findByTitle(String statusTitle);
    Optional<Status> findByColor(String statusColor);
}
