package com.chismografo.Exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ProfesorExceptions extends RuntimeException {
    private final HttpStatus status;

    public ProfesorExceptions(ProfesorType type) {
        super(type.getMensaje());
        this.status = type.getStatus();
    }
}
