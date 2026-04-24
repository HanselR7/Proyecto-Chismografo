package com.chismografo.Excepciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TutorErrorType {
    NOT_TUTOR_CONTENT("No se encontraron tutores.", HttpStatus.OK),
    ERROR_TUTOR_GUARDAR("Error al momento de guardar al tutor.", HttpStatus.INTERNAL_SERVER_ERROR),
    ERROR_TUTOR_ACTUALIZAR("Error al momento de actualizar al tutor.", HttpStatus.INTERNAL_SERVER_ERROR),
    ERROR_TUTOR_ELIMINAR("Error al momento de eliminar al tutor.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String mensaje;
    private final HttpStatus status;
}
