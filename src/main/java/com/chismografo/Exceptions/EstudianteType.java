package com.chismografo.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum EstudianteType {
    NOT_ESTUDIANTE_CONTENT("No se encontraron estudiantes. :(", HttpStatus.OK),
    ERROR_ESTUDIANTE_GUARDAR("Error al guardar el estudiante >:(", HttpStatus.INTERNAL_SERVER_ERROR),
    ERROR_ACTUALIZAR_ESTUDIANTE("Error al actualizar el estuidiante <:(", HttpStatus.INTERNAL_SERVER_ERROR),
    ERROR_ELIMINAR_ESTUDIANTE("Error al eliminar el estuidiante :^(", HttpStatus.INTERNAL_SERVER_ERROR);

    private String mensaje;
    private HttpStatus status;



}
