package ru.media.accounting.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import ru.media.accounting.dto.UserRequest;
import ru.media.accounting.dto.UserRequestUpdate;
import ru.media.accounting.dto.UserResponse;

import java.util.List;

public interface UserController {

    @Operation(summary = "Найти пользователя по username.", description = "Ищет пользователя по username.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь найден", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "Не найден пользователь", content = {
                    @Content(mediaType = "text/plain",
                            schema = @Schema(example = "Пользователь с \"username\" не найден"))
            })
    })
    ResponseEntity<UserResponse> findByUsername(@Parameter(name = "username", description = "username пользователя",
                                                examples = {@ExampleObject(name = "Администратор", value = "admin",
                                                description = "Найти пользователя с username \"admin\""),
                                                @ExampleObject(name = "Пользователь", value = "user",
                                                description = "Найти пользователя с username \"user\"")}
                                                ) String username);

    @Operation(summary = "Найти пользователя по email.", description = "Ищет пользователя по email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь найден", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "Не найден пользователь", content = {
                    @Content(mediaType = "text/plain",
                            schema = @Schema(example = "Пользователь с \"email\" не найден"))
            })
    })
    ResponseEntity<UserResponse> findByEmail(@Parameter(name = "email", description = "email пользователя",
                                            examples = {@ExampleObject(name = "Администратор", value = "admin@admin.com",
                                            description = "Найти пользователя с email \"admin@admin.com\""),
                                            @ExampleObject(name = "Пользователь", value = "user@user.com",
                                            description = "Найти пользователя с email \"user@user.com\"")}
                                            ) String email);

    @Operation(summary = "Создать пользователя.", description = "Создает нового пользователя с ролью USER. Только для администратора.")
    ResponseEntity<UserResponse> save(UserRequest userRequest);

    @Operation(summary = "Обновить пользователя.", description = "Обновляет пользователя. Только для администратора.")
    ResponseEntity<UserResponse> update(UserRequestUpdate userRequestUpdate);

    @Operation(summary = "Удалить пользователя.", description = "Удаляет пользователя по username. Только для администратора.")
    ResponseEntity<String> delete(@Parameter(name = "username", description = "username пользователя",
                                examples = {@ExampleObject(name = "Пользователь", value = "user",
                                description = "Удалить пользователя с username \"user\"")}
                                ) String username);

    @Operation(summary = "Получить список пользователей.", description = "Получает список всех пользователей. Только для администратора.")
    List<UserResponse> findAll();
}
