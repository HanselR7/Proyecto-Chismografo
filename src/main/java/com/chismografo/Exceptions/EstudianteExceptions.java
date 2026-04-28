package com.chismografo.Exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EstudianteExceptions extends RuntimeException {
    private final HttpStatus status;

    public EstudianteExceptions(EstudianteType type) {
        super(type.getMensaje());
        this.status = type.getStatus();
    }
}
