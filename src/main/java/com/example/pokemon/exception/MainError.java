package com.example.pokemon.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MainError {

    UNKNOWN_ERROR(-1L, HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND_ERROR(1L, HttpStatus.NOT_FOUND),
    EXIST_ERROR(2L, HttpStatus.FOUND),
    BAD_REQUEST_ERROR(3L, HttpStatus.BAD_REQUEST),
    ACCESS_ERROR(4L, HttpStatus.FORBIDDEN);


    private final Long internalCode;
    private final HttpStatus status;
}

