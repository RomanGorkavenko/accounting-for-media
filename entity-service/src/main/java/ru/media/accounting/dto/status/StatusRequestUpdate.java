package ru.media.accounting.dto.status;

import lombok.Data;

@Data
public class StatusRequestUpdate {

    private String oldTitle;
    private String newTitle;
    private String newColor;

}
