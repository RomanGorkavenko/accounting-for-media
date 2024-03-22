package ru.media.accounting.api.controller;

import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.media.accounting.exception.CustomAccessDeniedException;
import ru.media.accounting.exception.ElementAlreadyExistsException;

import java.util.NoSuchElementException;
import java.util.logging.Level;

/**
 * Контроллер для обработки ошибок.
 * В микросервисе media-service.
 */
@Log
@RestControllerAdvice
public class MediaServiceGlobalControllerExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> noSuchElementException(NoSuchElementException e) {
        log.log(Level.WARNING, e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ElementAlreadyExistsException.class)
    public ResponseEntity<String> elementAlreadyExistsException(ElementAlreadyExistsException e) {
        log.log(Level.WARNING, e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(CustomAccessDeniedException.class)
    public ResponseEntity<String> customAccessDeniedException(CustomAccessDeniedException e) {
        log.log(Level.WARNING, e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

}
