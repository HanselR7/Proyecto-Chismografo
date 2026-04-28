package com.chismografo.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TutorType {
    NOT_TUTOR_CONTENT("No se encontraron tutores.", HttpStatus.OK),
    ERROR_TUTOR_GUARDAR("Error al intentar guardar al tutor", HttpStatus.INTERNAL_SERVER_ERROR),
    ERROR_TUTOR_ACTUALIZAR("Error al intentar actualizar al tutor", HttpStatus.INTERNAL_SERVER_ERROR),
    ERROR_TUTOR_ELIMINAR("Error al intentar eliminar al tutor", HttpStatus.INTERNAL_SERVER_ERROR);

    private String message;
    private HttpStatus status;
}
