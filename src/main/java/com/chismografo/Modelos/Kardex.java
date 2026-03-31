package com.chismografo.Modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Kardex {
    private String materiaId;
    private List<Parcial> parciales = new ArrayList<>();
    private double promedioFinal;
}
