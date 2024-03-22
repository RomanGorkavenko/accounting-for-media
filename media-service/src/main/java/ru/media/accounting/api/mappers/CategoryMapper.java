package ru.media.accounting.api.mappers;

import org.mapstruct.Mapper;
import ru.media.accounting.dto.category.CategoryResponse;
import ru.media.accounting.model.Category;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    List<CategoryResponse> toDto(List<Category> categories);
    CategoryResponse toDto(Category category);
    Category toEntity(CategoryResponse categoryResponse);
}
