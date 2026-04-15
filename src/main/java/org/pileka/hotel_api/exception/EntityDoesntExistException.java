package org.pileka.hotel_api.exception;

public class EntityDoesntExistException extends RuntimeException {
    public EntityDoesntExistException(String message) {
        super(message);
    }
}
