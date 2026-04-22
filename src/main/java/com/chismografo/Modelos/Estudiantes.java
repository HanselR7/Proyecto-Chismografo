package com.chismografo.Modelos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data //Hace que los atributos tengan getter y setters gracias a lombok
@NoArgsConstructor //Genera constructor vacio
@AllArgsConstructor //Genera constructor con todos los atributos
@Builder

public class Estudiantes extends Usuario {
    private String tutorId;
    private List<String> materiasInscritas = new ArrayList<>();
    private List<Kardex> registroAcademico = new ArrayList<>();
}
