package ru.media.accounting.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.media.accounting.api.feign.UserFeignClient;
import ru.media.accounting.dto.UserResponse;
import ru.media.accounting.model.Media;
import ru.media.accounting.repository.MediaRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final MediaRepository mediaRepository;
    private final UserFeignClient userFeignClient;

    public List<Media> findByUsername(String username) {
        UserResponse user;
        try {
            user = userFeignClient.findByUsername(username).getBody();
        } catch (FeignException.NotFound e) {
            throw new NoSuchElementException("Пользователь с username = " + username + " не найден");
        }
        Long userId = Objects.requireNonNull(user).getId();
        List<Media> media = mediaRepository.findByUserId(userId);
        if (media.isEmpty()) {
            throw new NoSuchElementException("Не найдено носителей у пользователя " + username);
        }
        return mediaRepository.findByUserId(userId);
    }

    public List<Media> findByUserEmail(String email) {
        UserResponse user;
        try {
            user = userFeignClient.findByEmail(email).getBody();
        } catch (FeignException.NotFound e) {
            throw new NoSuchElementException("Пользователь с username = " + email + " не найден");
        }
        Long userId = Objects.requireNonNull(user).getId();
        List<Media> media = mediaRepository.findByUserId(userId);
        if (media.isEmpty()) {
            throw new NoSuchElementException("Не найдено носителей у пользователя " + email);
        }
        return mediaRepository.findByUserId(userId);
    }
}
