package ru.media.accounting.api.security.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.media.accounting.api.security.UserServiceJwtTokenProvider;
import ru.media.accounting.dto.auth.JwtRequest;
import ru.media.accounting.dto.auth.JwtResponse;
import ru.media.accounting.model.User;
import ru.media.accounting.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceAuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserServiceJwtTokenProvider jwtTokenProvider;

    /**
     * Проверка авторизации пользователя.
     * @param loginRequest - запрос на авторизацию
     * @return {@link JwtResponse} dto - ответ на запрос
     */
    public JwtResponse login(JwtRequest loginRequest) {
        JwtResponse jwtResponse = new JwtResponse();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        User user = userService.findByUsername(loginRequest.getUsername());
        jwtResponse.setId(user.getId());
        jwtResponse.setUsername(user.getUsername());
        jwtResponse.setAccessToken(jwtTokenProvider.createAccessToken(user.getId(),
                user.getUsername(), user.getRoles()));
        jwtResponse.setRefreshToken(jwtTokenProvider.createRefreshToken(user.getId(), user.getUsername()));

        return jwtResponse;
    }

    /**
     * Обновление токена.
     * @param refreshToken - токен для обновления.
     * @return {@link JwtResponse} dto - ответ на запрос
     */
    public JwtResponse refresh(String refreshToken) {
        return jwtTokenProvider.refreshUserTokens(refreshToken);
    }

}
