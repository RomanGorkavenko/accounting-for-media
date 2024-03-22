package ru.media.accounting.dto.media;

import lombok.Data;

@Data
public class MediaRequestUpdate {

    private Long number;
    private String title;
    private String description;
    private String statusTitle;
}
