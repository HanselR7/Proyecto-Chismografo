package com.chismografo.Excepciones;

import com.chismografo.Modelos.Tutor;
import com.chismografo.utils.ModelResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TutorError.class)
    public ResponseEntity<ModelResponse<List<Tutor>>> handleTutorError(TutorError ex) {
        return ResponseEntity.ok().body(
                ModelResponse.<List<Tutor>>builder()
                        .mensaje(ex.getMessage())
                        .codigo(ex.getStatus().value())
                        .data(Collections.emptyList())
                        .build());
    }
}