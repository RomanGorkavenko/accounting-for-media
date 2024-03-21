package ru.media.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.media.accounting.model.PlacementObject;

import java.util.Optional;

public interface PlacementObjectRepository extends JpaRepository<PlacementObject, Long> {
    Optional<PlacementObject> findByTitle(String title);
}
