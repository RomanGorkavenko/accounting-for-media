package ru.media.accounting.dto.media;

import lombok.Data;
import ru.media.accounting.model.Category;
import ru.media.accounting.model.PlacementObject;
import ru.media.accounting.model.Status;

import java.time.LocalDate;

@Data
public class MediaResponse {

    private Long id;
    private String title;
    private String description;
    private Long number;
    private String image_link;
    private PlacementObject object;
    private Long userId;
    private LocalDate start_date;
    private Integer service_life;
    private LocalDate end_date;
    private Category category;
    private Status status;
}
