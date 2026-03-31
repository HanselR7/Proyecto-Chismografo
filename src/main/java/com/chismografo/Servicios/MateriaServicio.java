package com.chismografo.Servicios;

import com.chismografo.Modelos.Materia;
import com.chismografo.Repositorios.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaServicio {

    @Autowired
    private MateriaRepository materiaRepository;

    public List<Materia> obtenerMaterias(){
        return materiaRepository.findAll();
    }


}
