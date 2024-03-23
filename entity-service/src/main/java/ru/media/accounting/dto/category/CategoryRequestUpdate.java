package ru.media.accounting.dto.category;

import lombok.Data;

@Data
public class CategoryRequestUpdate {

    private String oldTitle;
    private String newTitle;

}
