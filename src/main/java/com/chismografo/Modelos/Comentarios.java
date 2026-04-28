package com.chismografo.Modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comentarios")

public class Comentarios  {

    @Id
    private String idUsuario;
    private String fechaDePublicacion ;
    private String descripcion;
}
