package com.chismografo.Exceptions;

import com.chismografo.Modelos.Estudiantes;
import com.chismografo.utils.ModelResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
@RestControllerAdvice
public class GlobalErrorException {

    @ExceptionHandler(EstudianteExceptions.class)
    public ResponseEntity<ModelResponse> handleEstudiantesException(EstudianteExceptions estudianteExceptions){
        return ResponseEntity.status(200).body(
                ModelResponse.builder()
                        .mensaje(estudianteExceptions.getMessage())
                        .codigo(estudianteExceptions.getStatus().value())
                        .build()
        );
    }
}
