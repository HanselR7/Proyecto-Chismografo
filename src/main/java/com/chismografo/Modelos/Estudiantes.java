package com.chismografo.Modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Data //Hace que los atributos tengan getter y setters gracias a lombok
@NoArgsConstructor //Genera constructor vacio
@AllArgsConstructor //Genera constructor con todos los atributos
@Document(collection = "estudiantes")
public class Estudiantes extends Usuario {
    private String tutorId;
    private List<String> materiasInscritas = new ArrayList<>();
    private List<Kardex> registroAcademico = new ArrayList<>();
}
