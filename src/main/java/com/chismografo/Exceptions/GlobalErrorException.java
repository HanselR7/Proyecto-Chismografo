package com.chismografo.Exceptions;

import com.chismografo.Modelos.Profesor;
import com.chismografo.utils.ModelResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
@RestControllerAdvice
public class GlobalErrorException {
@ExceptionHandler(ProfesorExceptions.class)
    public ResponseEntity<ModelResponse> handleException(){
    return ResponseEntity.ok(
            ModelResponse.builder()
                    .mensaje(e.getMessage)
                    .codigo(e.getStatus().value())
                    .build()
    );

}
}
