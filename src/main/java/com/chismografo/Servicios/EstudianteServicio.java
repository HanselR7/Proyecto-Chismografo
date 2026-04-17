package com.chismografo.Servicios;

import com.chismografo.Modelos.Estudiantes;
import com.chismografo.Repositorios.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


//Controllador, como tipo controlador
@Service
public class EstudianteServicio {
    //Etiqueta para inyectar el repository (Base de datos)
    @Autowired
    private EstudianteRepository estudianteRepository;

    public List<Estudiantes> obtenerTodosEstudiantes() {
        return estudianteRepository.findAll();
    }

    public Estudiantes guardarEstudiante(Estudiantes estudiante){
        return estudianteRepository.save(estudiante);
    }

    public List<Estudiantes> obtenerEstudiantesByRol(){return  estudianteRepository.findByRol("estudiante");}

    public boolean eliminarEstudiante(String estudianteId){
        if(estudianteRepository.existsById(estudianteId)){
            estudianteRepository.deleteById(estudianteId);
            return true;
        }
        return false;
    }

}
