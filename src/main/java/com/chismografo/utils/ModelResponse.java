package com.chismografo.utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelResponse<T> {
    private String mensaje;
    private int codigo;
    private T data;
}