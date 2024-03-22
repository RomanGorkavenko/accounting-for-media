package ru.media.accounting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.media.accounting.dto.status.StatusRequest;
import ru.media.accounting.exception.ElementAlreadyExistsException;
import ru.media.accounting.model.Status;
import ru.media.accounting.repository.StatusRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class StatusService {

    private final StatusRepository statusRepository;
    private final MediaService mediaService;

    /**
     * Получение статуса.
     * @param statusTitle название статуса.
     * @return статус.
     * @throws NoSuchElementException если статуса нет.
     */
    public Status findByTitle(String statusTitle) {
        return statusRepository.findByTitle(statusTitle)
                .orElseThrow(() -> new NoSuchElementException("Нет статуса status = " + statusTitle));
    }

    /**
     * Получение списка статусов.
     * @return список статусов.
     * @throws NoSuchElementException если статусов нет.
     */
    public List<Status> findAll(){
        List<Status> statuses = statusRepository.findAll();
        if (statuses.isEmpty()) {
            throw new NoSuchElementException("Статусы не найдены");
        }
        return statuses;
    }

    /**
     * Сохранение статуса.
     * @param statusRequest запрос на сохранение статуса.
     * @return {@link Status} статус.
     * @throws ElementAlreadyExistsException если статус уже существует.
     */
    public Status save(StatusRequest statusRequest) {
        if(statusRepository.findByTitle(statusRequest.getTitle()).isPresent() ||
                statusRepository.findByColor(statusRequest.getColor()).isPresent()) {
            throw new ElementAlreadyExistsException("Статус уже существует.");
        }

        Status status = new Status();
        status.setTitle(statusRequest.getTitle());
        status.setColor(statusRequest.getColor());

        return statusRepository.save(status);
    }

    /**
     * Обновление статуса.
     * @param statusRequest запрос на обновление статуса.
     * @return {@link Status} статус.
     */
    public Status update(StatusRequest statusRequest) {
        Status status = findByTitle(statusRequest.getTitle());
        status.setTitle(statusRequest.getTitle());
        status.setColor(statusRequest.getColor());
        return statusRepository.save(status);
    }

    /**
     * Удаление статуса. Если статус используется в медиа, то выбрасывается исключение.
     * @param statusRequest запрос на удаление статуса.
     * @throws ElementAlreadyExistsException Если статус используется в медиа.
     */
    public void delete(StatusRequest statusRequest) {
        Status status = findByTitle(statusRequest.getTitle());
        if (mediaService.findByStatus(status).isEmpty()) {
            statusRepository.delete(status);
        } else {
            throw new ElementAlreadyExistsException("Статус используется в носителе.");
        }
    }
}
