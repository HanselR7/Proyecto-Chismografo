package com.chismografo.Controladores;

import com.chismografo.Modelos.Materia;
import com.chismografo.Servicios.MateriaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/materia")
public class MateriaController {

    @Autowired
    private MateriaServicio materiaServicio;

    @GetMapping("/obtenerMaterias")
    public List<Materia> obtenerMaterias(){
        return materiaServicio.obtenerMaterias();
    }
}
