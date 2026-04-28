package com.chismografo.Exceptions;

import com.chismografo.Modelos.Materia;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MateriaExceptions extends RuntimeException {
    private final HttpStatus status;

    public MateriaExceptions(MateriaType type){
        super(type.getMessage());
        this.status = type.getStatus();
    }
}
