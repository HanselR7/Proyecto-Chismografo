package com.chismografo.Modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor //no genera un constructor vacio
@AllArgsConstructor //crea un constructor con todas sus variables

public class Profesor {
 private String especialidad;
 private List<String> materias = new ArrayList<>();
}
