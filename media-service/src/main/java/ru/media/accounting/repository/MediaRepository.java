package ru.media.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.media.accounting.model.Category;
import ru.media.accounting.model.Media;
import ru.media.accounting.model.Status;

import java.util.List;
import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Long> {

    List<Media> findByUserId(Long userId);
    List<Media> findByUserIdOrderByTitleAsc(Long userId);
    List<Media> findByCategoryTitleAndUserIdOrderByStatusAsc(String categoryTitle, Long userId);
    List<Media> findByStatusTitleAndUserIdOrderByStatusTitleAsc(String statusTitle, Long userId);
    List<Media> findByTitle(String title);
    Optional<Media> findByNumber(Long number);
    Optional<Media> findByNumberAndUserId(Long number, Long userId);
    List<Media> findByStatus(Status status);
    List<Media> findByCategory(Category category);

    @Query("""
            SELECT COUNT(m.title)
            FROM Media m
            """)
    Optional<Long> countAll();

    @Query("""
            SELECT COUNT(m.title)
            FROM Media m
            WHERE (:title IS NULL
            OR :title=''
            OR LOWER(m.title)
            LIKE LOWER(concat('%', :title, '%')))
            """)
    Optional<Long> countTitle(@Param("title") String title);

    @Query("""
            SELECT COUNT(m.title)
            FROM Media m
            WHERE (:statusTitle IS NULL
            OR :statusTitle=''
            OR LOWER(m.status.title)
            LIKE LOWER(concat('%', :statusTitle, '%')))
            """)
    Optional<Long> countStatusTitle(@Param("statusTitle") String statusTitle);

    @Query("""
            SELECT COUNT(m.title)
            FROM Media m
            WHERE (:categoryTitle IS NULL
            OR :categoryTitle=''
            OR LOWER(m.category.title)
            LIKE LOWER(concat('%', :statusTitle, '%')))
            """)
    Optional<Long> countCategoryTitle(@Param("categoryTitle") String categoryTitle);
}
