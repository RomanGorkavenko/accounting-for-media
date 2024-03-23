package ru.media.accounting.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.media.accounting.api.feign.UserFeignClient;
import ru.media.accounting.api.security.MediaServiceJwtEntity;
import ru.media.accounting.dto.media.MediaRequest;
import ru.media.accounting.dto.media.MediaRequestUpdate;
import ru.media.accounting.dto.user.UserResponse;
import ru.media.accounting.exception.CustomAccessDeniedException;
import ru.media.accounting.exception.ElementAlreadyExistsException;
import ru.media.accounting.model.*;
import ru.media.accounting.repository.MediaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final MediaRepository mediaRepository;
    private final UserFeignClient userFeignClient;
    private final CategoryService categoryService;
    private final PlacementObjectService placementObjectService;
    private final StatusService statusService;

    /**
     * Поиск носителей по названию.
     * @param title название носителя.
     * @return {@link List<Media>} список носителей.
     * @throws NoSuchElementException если носители не найдены.
     */
    public List<Media> findByTitle(String title) {
        List<Media> media = mediaRepository.findByTitle(title);
        if (media.isEmpty()) {
            throw new NoSuchElementException("Не найдено носителей носителей с названием " + title);
        }
        return media;
    }

    /**
     * Поиск носителя по номеру.
     * @param number номер носителя.
     * @return {@link Media} носитель.
     */
    public Media findByNumber(Long number) {
        return mediaRepository.findByNumber(number)
                .orElseThrow(() -> new NoSuchElementException("Не найдено носителей с номером " + number));
    }

    /**
     * Поиск носителя по названию и пользователю.
     * @param number номер носителя.
     * @return {@link Media} носитель.
     * @throws NoSuchElementException если носитель не найден.
     */
    public Media findByNumberAndUserId(Long number) {
        return mediaRepository.findByNumberAndUserId(number, getUserId())
                .orElseThrow(() -> new NoSuchElementException("У вас нет носителей с номером " + number));
    }

    /**
     * Передача носителя другому пользователю.
     * @param number название носителя.
     * @param username username пользователя.
     * @return {@link Media} обновленный носитель.
     */
    public Media changeUser(Long number, String username) {
        Media media = findByNumber(number);

        UserResponse user;
        try {
            user = userFeignClient.findByUsername(username).getBody();
        } catch (FeignException.NotFound e) {
            throw new NoSuchElementException(e.getLocalizedMessage());
        } catch (FeignException.Forbidden e) {
            throw new CustomAccessDeniedException();
        }

        assert user != null;
        media.setUserId(user.getId());

        return mediaRepository.save(media);
    }

    /**
     * Поиск носителей по пользователю.
     * @param username username пользователя.
     * @return {@link List<Media>} носитель.
     * @throws NoSuchElementException если носитель или пользователя не найдены.
     */
    public List<Media> findByUsername(String username) {
        UserResponse user;
        try {
            user = userFeignClient.findByUsername(username).getBody();
        } catch (FeignException.NotFound e) {
            throw new NoSuchElementException("Пользователь с username = " + username + " не найден");
        } catch (FeignException.Forbidden e) {
            throw new CustomAccessDeniedException();
        }
        Long userId = Objects.requireNonNull(user).getId();
        List<Media> media = mediaRepository.findByUserId(userId);
        if (media.isEmpty()) {
            throw new NoSuchElementException("Не найдено носителей у пользователя " + username);
        }
        return media;
    }

    /**
     * Поиск носителей по почте пользователя.
     * @param email email пользователя.
     * @return {@link List<Media>} список носителей.
     * @throws NoSuchElementException если носитель или пользователя не найдены.
     */
    public List<Media> findByUserEmail(String email) {
        UserResponse user;
        try {
            user = userFeignClient.findByEmail(email).getBody();
        } catch (FeignException.NotFound e) {
            throw new NoSuchElementException("Пользователь с username = " + email + " не найден");
        } catch (FeignException.Forbidden e) {
            throw new CustomAccessDeniedException();
        }
        Long userId = Objects.requireNonNull(user).getId();
        List<Media> media = mediaRepository.findByUserId(userId);
        if (media.isEmpty()) {
            throw new NoSuchElementException("Не найдено носителей у пользователя " + email);
        }
        return media;
    }

    /**
     * Получение всех носителей. Возвращает список носителей, которые есть в базе.
     * @return {@link List<Media>} список носителей.
     */
    public List<Media> findAll() {
        return mediaRepository.findAll();
    }

    /**
     * Получение всех носителей пользователя. Возвращает список носителей, которые есть у пользователя.
     * @return {@link List<Media>} список носителей.
     */
    public List<Media> findAllByUser() {
        List<Media> media = mediaRepository.findByUserIdOrderByTitleAsc(getUserId());
        if (media.isEmpty()) {
            throw new NoSuchElementException("Не найдено носителей у пользователя.");
        }
        return media;
    }

    /**
     * Получение всех носителей пользователя по названию категории.
     * С сортировкой по статусу.
     * @param categoryTitle название категории.
     * @return {@link List<Media>} список носителей.
     */
    public List<Media> findAllByCategoryAndUser(String categoryTitle){
        List<Media> media = mediaRepository.findByCategoryTitleAndUserIdOrderByStatusAsc(categoryTitle, getUserId());
        if (media.isEmpty()) {
            throw new NoSuchElementException("Не найдено носителей у пользователя по категории " + categoryTitle);
        }
        return media;
    }

    /**
     * Получение всех носителей пользователя по названию статуса.
     * С сортировкой по статусу.
     * @param statusTitle название статуса.
     * @return {@link List<Media>} список носителей.
     * @throws NoSuchElementException если носители со статусом не найдены.
     */
    public List<Media> findAllByStatusAndUser(String statusTitle){
        List<Media> media = mediaRepository.findByStatusTitleAndUserIdOrderByStatusTitleAsc(statusTitle, getUserId());
        if (media.isEmpty()) {
            throw new NoSuchElementException("Не найдено носителей у пользователя по категории " + statusTitle);
        }
        return media;
    }

    /**
     * Добавление носителя.
     * @param mediaRequest запрос на добавление носителя.
     * @return {@link Media} носитель.
     */
    public Media add(MediaRequest mediaRequest) {
        if(mediaRepository.findByNumber(mediaRequest.getNumber()).isPresent()) {
            throw new ElementAlreadyExistsException(
                    "Носитель с номером " + mediaRequest.getNumber() + " уже существует");
        }

        Category category = categoryService.findByTitle(mediaRequest.getCategoryTitle());
        PlacementObject object = placementObjectService.findByTitle(mediaRequest.getObjectTitle());
        Status status = statusService.findByTitle(mediaRequest.getStatusTitle());

        Media media = new Media();
        media.setTitle(mediaRequest.getTitle());
        media.setDescription(mediaRequest.getDescription());
        media.setNumber(mediaRequest.getNumber());
        media.setObject(object);
        media.setUserId(getUserId());
        media.setStart_date(LocalDate.now());
        media.setService_life(mediaRequest.getServiceLife());
        media.setEnd_date(media.getStart_date().plusDays(mediaRequest.getServiceLife()));
        media.setCategory(category);
        media.setStatus(status);

        return mediaRepository.save(media);
    }

    /**
     * Обновление носителя.
     * Авторизованный пользователь может обновить только свои носители.
     * @param mediaRequestUpdate запрос на обновление носителя.
     * @return {@link Media} носитель.
     */
    public Media update(MediaRequestUpdate mediaRequestUpdate) {
        if(mediaRepository.findByNumber(mediaRequestUpdate.getNumber()).isPresent()) {
            throw new ElementAlreadyExistsException(
                    "Носитель с номером " + mediaRequestUpdate.getNumber() + " уже существует");
        }

        Media media = findByNumberAndUserId(mediaRequestUpdate.getNumber());
        Status status = statusService.findByTitle(mediaRequestUpdate.getStatusTitle());
        media.setTitle(mediaRequestUpdate.getTitle());
        media.setDescription(mediaRequestUpdate.getDescription());
        media.setStatus(status);

        return mediaRepository.save(media);
    }

    /**
     * Удаление носителя.
     * Авторизованный пользователь может удалить только свои носители.
     * @param number номер носителя.
     */
    public void delete(Long number) {
        mediaRepository.delete(findByNumberAndUserId(number));
    }

    /**
     * Получение носителя по статусу.
     * @param status статус носителя.
     * @return {@link List<Media>} список носителей.
     */
    public List<Media> findByStatus(Status status) {
        return mediaRepository.findByStatus(status);
    }

    /**
     * Получение носителей по категории.
     * @param category категория носителя.
     * @return {@link List<Media>} список носителей.
     */
    public List<Media> findByCategory(Category category) {
        return mediaRepository.findByCategory(category);
    }

    /**
     * Подсчет количества носителей.
     * @return количество носителей.
     * @throws NoSuchElementException если носителей не найдено.
     */
    public Long countAll() {
        return mediaRepository.countAll()
                .orElseThrow(() -> new NoSuchElementException("Не найдено носителей"));
    }

    /**
     * Подсчет количества носителей по названию.
     * @param title название носителя.
     * @return количество носителей.
     * @throws NoSuchElementException если носителей не найдено.
     */
    public Long countTitle(String title) {
        return mediaRepository.countTitle(title)
                .orElseThrow(() -> new NoSuchElementException("Не найдено носителей"));
    }

    /**
     * Подсчет количества носителей по статусу.
     * @param statusTitle статус носителя.
     * @return количество носителей.
     * @throws NoSuchElementException если носителей не найдено.
     */
    public Long countStatusTitle(String statusTitle) {
        return mediaRepository.countStatusTitle(statusTitle)
                .orElseThrow(() -> new NoSuchElementException("Не найдено носителей"));
    }

    /**
     * Подсчет количества носителей по категории.
     * @param categoryTitle название категории носителя.
     * @return количество носителей
     * @throws NoSuchElementException если носителей не найдено.
     */
    public Long countCategoryTitle(String categoryTitle) {
        return mediaRepository.countCategoryTitle(categoryTitle)
                .orElseThrow(() -> new NoSuchElementException("Не найдено носителей"));
    }

    /**
     * Получение ID пользователя. Из авторизации.
     * @return ID пользователя.
     */
    private Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MediaServiceJwtEntity user = (MediaServiceJwtEntity) authentication.getPrincipal();
        return user.getUser().getId();
    }
}
