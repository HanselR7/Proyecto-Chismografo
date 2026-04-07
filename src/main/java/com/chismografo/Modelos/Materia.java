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
@AllArgsConstructor //Genera el contrsctor con todos los atributos de la clase
@Document(collection = "materias") //Enlazamos con nuestra coleccion de MongoDB
public class Materia {
    /*Atributos de clase*/
    @Id //Decmos que el siguiente atributo sera el id de la coleccion de mongo
    private String id;
    private String nombre;
    private String abreviacion;
    private String descripcion;
    private String profesorId;
    private String horario;
    private List<String> alumnos = new ArrayList<>();
}
