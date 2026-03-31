package com.chismografo.Controladores;

import com.chismografo.Modelos.Estudiantes;
import com.chismografo.Servicios.EstudianteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/estudiante")
public class EstudianteController {

    @Autowired
    private EstudianteServicio estudianteServicio;

    @GetMapping("/obtenerEstduaintes")
    public List<Estudiantes> obtenerEstudiantes(){
        return estudianteServicio.obtenerTodosEstudiantes();
    }
}
