package com.chismografo.Modelos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data //Genara los Getters & Stters
@NoArgsConstructor //Genera un constructor vacio
@AllArgsConstructor //Genera el constructor con todos los atributos de la clase
@Document(collection = "materias") //Enlazamos con nuestra coleccion de MongoDB
@Builder //Para construir el objeto

public class Materia {
    /*Atributos de clase*/
    @Id //Decimos que el siguiente atributo sera el id de la coleccion de mongo
    private String id;
    private String nombre;
    private String abreviacion;
    private String descripcion;
    private Integer gradoEscolar;
}
