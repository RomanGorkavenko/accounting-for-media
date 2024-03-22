package ru.media.accounting.dto.placement_object;

import lombok.Data;
import ru.media.accounting.model.Media;

import java.util.Set;

@Data
public class PlacementObjectResponse {

    private Long id;
    private String title;
    private String description;
    private Set<Media> medias;

}
