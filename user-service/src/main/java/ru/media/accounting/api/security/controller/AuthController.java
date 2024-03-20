package ru.media.accounting.api.security.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ru.media.accounting.dto.UserRequest;
import ru.media.accounting.dto.UserResponse;
import ru.media.accounting.dto.auth.JwtRequest;
import ru.media.accounting.dto.auth.JwtResponse;

public interface AuthController {

    @Operation(summary = "Аутентификация", description = "Получение токена")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Аутентификация прошла успешно", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JwtResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "Не найден пользователь", content = {
                    @Content(mediaType = "text/plain",
                            schema = @Schema(example = "Пользователь с username не найден"))
            })
    })
    JwtResponse login(JwtRequest loginRequest);

    @Operation(summary = "Регистрация", description = "Регистрация пользователя")
    UserResponse register(UserRequest userRequest);

    @Operation(summary = "Обновление access token", description = "Обновляет access token")
    JwtResponse refresh(String refreshToken);

}
