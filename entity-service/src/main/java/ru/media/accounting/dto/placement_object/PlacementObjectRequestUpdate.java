package ru.media.accounting.dto.placement_object;

import lombok.Data;

@Data
public class PlacementObjectRequestUpdate {

    private String oldTitle;
    private String newTitle;
    private String newDescription;

}
