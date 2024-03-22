package ru.media.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.media.accounting.model.Category;
import ru.media.accounting.model.Media;
import ru.media.accounting.model.Status;

import java.util.List;
import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Long> {

    List<Media> findByUserId(Long userId);
    List<Media> findByUserIdOrderByTitleAsc(Long userId);
    List<Media> findByCategoryTitleAndUserIdOrderByStatusAsc(String categoryTitle, Long userId);
    List<Media> findByTitle(String title);
    Optional<Media> findByNumber(Long number);
    List<Media> findByTitleAndUserId(String title, Long userId);
    Optional<Media> findByNumberAndUserId(Long number, Long userId);
    List<Media> findByStatus(Status status);
    List<Media> findByCategory(Category category);
}
