package ru.media.accounting.dto.placement_object;

import lombok.Data;
import ru.media.accounting.model.Media;

import java.util.Set;

@Data
public class PlacementObjectRequest {

    private String title;
    private String description;

}
