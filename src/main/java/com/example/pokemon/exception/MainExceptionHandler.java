package com.example.pokemon.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@RequiredArgsConstructor
public class MainExceptionHandler {

    @ExceptionHandler(MainException.class)
    public ResponseEntity<ExceptionBody> handleMainException(MainException ex) {
        return ResponseEntity.status(ex.getError().getStatus())
                .body(new ExceptionBody(
                        ex.getError().getInternalCode(),
                        ex.getError().getStatus(),
                        ex.getMessage(),
                        LocalDateTime.now()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionBody> handleAnyException(Exception ex) {

        MainException mainException = MainException.of(ex);
        return ResponseEntity.status(mainException.
                getError().
                getStatus()
        ).body(new ExceptionBody(
                mainException.getError().getInternalCode(),
                mainException.getError().getStatus(),
                mainException.getMessage(),
                LocalDateTime.now()
        ));
    }
}

