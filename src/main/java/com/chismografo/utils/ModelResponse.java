package com.chismografo.utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/*Clase para mandar el codigo de error*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelResponse<T> {
    private String mensaje;
    private int codigo;
    private T data;
}