package com.chismografo.Exceptions;

import com.chismografo.Modelos.Materia;
import com.chismografo.utils.ModelResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorException {
    @ExceptionHandler(MateriaExceptions.class)
    public ResponseEntity<ModelResponse> handleException(MateriaExceptions e){
        return ResponseEntity.ok(
                ModelResponse.builder()
                        .mensaje(e.getMessage())
                        .codigo(e.getStatus().value())
                        .build()
        );

    }
}
