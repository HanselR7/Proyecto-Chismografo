package com.chismografo.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MateriaType {
    NOT_MATERIA_CONTENT("No se encontraton materias.", HttpStatus.OK),
    ERROR_MATERIA_TO_SAVE("Error al intentar guaradar la materia", HttpStatus.INTERNAL_SERVER_ERROR),
    ERROR_MATERIA_TO_UPDATE("Error al intentar actualizar la materia", HttpStatus.INTERNAL_SERVER_ERROR),
    ERROR_MATERIA_TO_DELETE("Error al intentar eliminar la materia", HttpStatus.INTERNAL_SERVER_ERROR);

    private String message;
    private HttpStatus status;

}
