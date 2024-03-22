package ru.media.accounting.dto.media;

import lombok.Data;

@Data
public class MediaRequest {

    private String title;
    private String description;
    private Long number;
    private String objectTitle;
    private Integer serviceLife;
    private String categoryTitle;
    private String statusTitle;
}
