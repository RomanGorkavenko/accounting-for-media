package ru.media.accounting.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.media.accounting.dto.UserResponse;
import ru.media.accounting.model.User;

import java.util.List;

/**
 * Маппер для User.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Маппинг User в UserResponse.
     * @param user пользователь.
     * @return {@link UserResponse} dto пользователя для ответа.
     */
    UserResponse toDto(User user);

    /**
     * Маппинг List<User> в List<UserResponse>.
     * @param users список пользователей.
     * @return {@link List<UserResponse>} список пользователей для ответа.
     */
    List<UserResponse> toDto(List<User> users);

    /**
     * Маппинг UserResponse в User.
     * @param userResponse {@link UserResponse} dto пользователя для ввода.
     * @return {@link User} пользователь.
     */
    User toEntity(UserResponse userResponse);
}
