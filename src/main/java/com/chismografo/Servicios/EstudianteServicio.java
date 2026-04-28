package com.chismografo.Servicios;

import com.chismografo.Exceptions.EstudianteExceptions;
import com.chismografo.Exceptions.EstudianteType;
import com.chismografo.Modelos.Estudiantes;
import com.chismografo.Repositorios.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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

    public List<Estudiantes> obtenerEstudiantesByRol() {
        List<Estudiantes> listaEstudiantes = estudianteRepository.findByRol("estudiante");
        if(listaEstudiantes.isEmpty()){
            throw new EstudianteExceptions(EstudianteType.NOT_ESTUDIANTE_CONTENT);
        }
        return listaEstudiantes;
    }

    public boolean eliminarEstudiante(String estudianteId){
        if(estudianteRepository.existsById(estudianteId)){
            estudianteRepository.deleteById(estudianteId);
            return true;
        }
        return false;
    }

}
