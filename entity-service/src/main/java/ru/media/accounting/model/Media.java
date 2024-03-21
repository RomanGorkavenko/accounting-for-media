package ru.media.accounting.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "media")
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Long number;
    private String image_link;

    @ManyToOne
    @JoinColumn(name = "object_id", referencedColumnName = "id")
    private PlacementObject object;

    @Column(name = "user_id")
    private Long userId;

    private LocalDate start_date;
    private Integer service_life;
    private LocalDate end_date;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    public Media(String title, Long number, PlacementObject object, Long userId, Category category, Status status) {
        this.title = title;
        this.number = number;
        this.object = object;
        this.userId = userId;
        this.category = category;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Media media = (Media) o;
        return Objects.equals(id, media.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
