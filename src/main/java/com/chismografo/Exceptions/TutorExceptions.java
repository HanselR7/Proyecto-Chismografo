package com.chismografo.Exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TutorExceptions extends Exception {
    private final HttpStatus status;

    public TutorExceptions(TutorType type) {
        super(type.getMessage());
        this.status = type.getStatus();
    }
}
