package com.chismografo.Excepciones;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TutorError extends RuntimeException {
    private final HttpStatus status;

    public TutorError(TutorErrorType type) {
        super(type.getMensaje());
        this.status = type.getStatus();
    }
}
