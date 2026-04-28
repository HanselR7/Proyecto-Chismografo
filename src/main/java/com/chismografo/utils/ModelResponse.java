package com.chismografo.utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelResponse<T> {
    private String mensaje;
    private int codigo;
    private T data;
}