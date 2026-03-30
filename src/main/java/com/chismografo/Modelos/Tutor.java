package com.chismografo.Modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tutor extends Usuario {
    private String telefono;
    @Field("alumnos_cargo")
    private List<String> alumnosCargo = new ArrayList<>();
}
