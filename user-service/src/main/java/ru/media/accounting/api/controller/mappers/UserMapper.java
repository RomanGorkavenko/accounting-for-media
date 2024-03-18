package ru.media.accounting.api.controller.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.media.accounting.api.dto.UserResponse;
import ru.media.accounting.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toDto(User user);

    @Mapping(target = "roles", ignore = true)
    User toEntity(UserResponse userResponse);
}
