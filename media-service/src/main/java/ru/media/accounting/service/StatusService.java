package ru.media.accounting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.media.accounting.model.Status;
import ru.media.accounting.repository.StatusRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StatusService {

    private final StatusRepository statusRepository;

    public Status findByTitle(String statusTitle) {
        return statusRepository.findByTitle(statusTitle)
                .orElseThrow(() -> new NoSuchElementException("Нет статуса status = " + statusTitle));
    }
}
