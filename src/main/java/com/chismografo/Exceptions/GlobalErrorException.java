package com.chismografo.Exceptions;

import com.chismografo.utils.ModelResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalErrorException {

    @ExceptionHandler(TutorExceptions.class)
    public ResponseEntity<ModelResponse> handleException(TutorExceptions e) {
        return ResponseEntity.ok(
                ModelResponse.builder()
                        .mensaje(e.getMessage())
                        .codigo(e.getStatus().value())
                        .build()
        );
    }
}
