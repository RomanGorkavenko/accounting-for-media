package ru.media.accounting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.media.accounting.dto.placement_object.PlacementObjectRequest;
import ru.media.accounting.exception.ElementAlreadyExistsException;
import ru.media.accounting.model.PlacementObject;
import ru.media.accounting.repository.PlacementObjectRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PlacementObjectService {

    private final PlacementObjectRepository repository;

    /**
     * Получить объект размещения.
     * @param title название объекта размещения.
     * @return {@link PlacementObject} объект размещения.
     * @throws NoSuchElementException если объект не найден.
     */
    public PlacementObject findByTitle(String title) {
        return repository.findByTitle(title)
                .orElseThrow(() -> new NoSuchElementException("Объект object = " + title + " не найден"));
    }

    /**
     * Получить список объектов размещения.
     * @return список объектов размещения
     * @throws NoSuchElementException если объекты не найдены.
     */
    public List<PlacementObject> findAll(){
        List<PlacementObject> placementObjects = repository.findAll();
        if (placementObjects.isEmpty()) {
            throw new NoSuchElementException("Объекты размещения не найдены");
        }
        return placementObjects;
    }

    /**
     * Сохранить объект размещения.
     * @param placementObjectRequest запрос на сохранение объекта размещения.
     * @return объект размещения.
     * @throws ElementAlreadyExistsException если объект размещения уже существует.
     */
    public PlacementObject save(PlacementObjectRequest placementObjectRequest) {
        if(repository.findByTitle(placementObjectRequest.getTitle()).isPresent()) {
            throw new ElementAlreadyExistsException("Объект размещения уже существует");
        }

        PlacementObject placementObject = new PlacementObject();
        placementObject.setTitle(placementObjectRequest.getTitle());
        placementObject.setDescription(placementObjectRequest.getDescription());

        return repository.save(placementObject);
    }

    /**
     * Обновить объект размещения.
     * @param placementObjectRequest запрос на обновление объекта размещения.
     * @return объект размещения.
     */
    public PlacementObject update(PlacementObjectRequest placementObjectRequest) {
        PlacementObject placementObject = findByTitle(placementObjectRequest.getTitle());
        placementObject.setTitle(placementObjectRequest.getTitle());
        placementObject.setDescription(placementObjectRequest.getDescription());
        return repository.save(placementObject);
    }

    /**
     * Удалить объект размещения.
     * @param placementObjectRequest запрос на удаление объекта размещения.
     * @throws ElementAlreadyExistsException если у объекта размещения есть носители информации.
     */
    public void delete(PlacementObjectRequest placementObjectRequest) {
        PlacementObject placementObject = findByTitle(placementObjectRequest.getTitle());
        if (placementObject.getMedias().isEmpty()) {
            repository.delete(placementObject);
        } else {
            throw new ElementAlreadyExistsException("У объекта размещения есть носители информации.");
        }
    }
}
