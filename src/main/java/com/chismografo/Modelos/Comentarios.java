package com.chismografo.Modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Comentarios  {
    private String idUsuario;
    private String fechaDePublicacion ;
    private String descripcion;
}
