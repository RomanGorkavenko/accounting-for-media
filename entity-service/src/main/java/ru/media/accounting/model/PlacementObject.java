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
public class PlacementObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "object")
    private Set<Media> medias;

    public PlacementObject(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlacementObject placementObject = (PlacementObject) o;
        return java.util.Objects.equals(id, placementObject.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
}
