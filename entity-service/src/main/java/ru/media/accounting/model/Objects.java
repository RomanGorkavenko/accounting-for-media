package ru.media.accounting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "objects")
public class Objects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "object")
    private Set<Media> medias;

    public Objects(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Objects objects = (Objects) o;
        return java.util.Objects.equals(id, objects.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
}
