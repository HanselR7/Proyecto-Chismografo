package com.chismografo.Modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data //Genara los Getters & Stters
@NoArgsConstructor //Genera un constructor vacio
@AllArgsConstructor //Genera el constructor con todos los atributos de la clase
@Document(collection = "grupos") //Enlazamos con nuestra coleccion de MongoDB

public class Grupo {
    /*Atributos de clase*/
    @Id //Indicamos que el siguiente atributo sera el id de la coleccion de mongo
    private String id_grupo;
    private String id_materia;
    private String id_profesor;
    private List<String> ids_estuduante;
    private String nombre_grupo;
    private String codigo_acceso;
    private String grado;
    private String hoarario_materia;
}
