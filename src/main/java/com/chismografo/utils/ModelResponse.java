package com.chismografo.utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/*Clase para mandar el codigo de error*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelResponse<T> {
    private String mensaje;
    private int codigo;
    private T data;
}