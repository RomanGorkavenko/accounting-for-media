package ru.media.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.media.accounting.model.Media;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Long> {

    List<Media> findByUserId(Long userId);
}
