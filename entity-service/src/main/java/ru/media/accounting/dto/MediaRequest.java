package ru.media.accounting.dto;

import lombok.Data;
import ru.media.accounting.model.Category;
import ru.media.accounting.model.Objects;
import ru.media.accounting.model.Status;

@Data
public class MediaRequest {

    private String title;
    private String description;
    private Long number;
    private Objects object;
    private Integer service_life;
    private Category category;
    private Status status;
}
