package ru.media.accounting.dto.exception;

public class ElementAlreadyExistsException extends RuntimeException {

    public ElementAlreadyExistsException() {
    }
    public ElementAlreadyExistsException(String message) {
        super(message);
    }
}
