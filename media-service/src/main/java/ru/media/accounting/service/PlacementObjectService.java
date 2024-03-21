package ru.media.accounting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.media.accounting.model.PlacementObject;
import ru.media.accounting.repository.PlacementObjectRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PlacementObjectService {

    private final PlacementObjectRepository repository;

    public PlacementObject findByTitle(String title) {
        return repository.findByTitle(title)
                .orElseThrow(() -> new NoSuchElementException("Объект object = " + title + " не найден"));
    }
}
