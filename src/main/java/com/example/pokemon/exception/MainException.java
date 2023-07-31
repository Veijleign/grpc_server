package com.example.pokemon.exception;

import lombok.Getter;

@Getter
public class MainException extends RuntimeException {

    private final MainError error;

    private MainException(MainError error, String message) {
        super(message);
        this.error = error;
    }

    public static MainException of(MainError error, String message) {
        throw new MainException(error, message);
    }

    public static MainException of(Exception exception) {
        if (exception.getClass()
                .isAssignableFrom(MainException.class)) {
            throw (MainException) exception; // ???
        }
        throw new MainException(MainError.UNKNOWN_ERROR, exception.getMessage()); // ???
    }
}

