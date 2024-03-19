package ru.media.accounting.api.provider;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import ru.media.accounting.dto.UserResponse;

import java.util.NoSuchElementException;

@Component
public class UserProvider {

    public UserResponse getUser(String username) {
        UserResponse response;
        try {
            response = WebClient.create("http://localhost:8765/user-service/api/user/" + username)
                    .get()
                    .retrieve()
                    .bodyToMono(UserResponse.class)
                    .block();
            return response;
        } catch (WebClientResponseException.NotFound e) {
            throw new NoSuchElementException("Пользователь с username = " + username + " не найден");
        }
    }
}
