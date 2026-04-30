package com.chismografo.Servicios;

import com.chismografo.Exceptions.ProfesorExceptions;
import com.chismografo.Exceptions.ProfesorType;
import com.chismografo.Modelos.Profesor;
import com.chismografo.Repositorios.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorServico {

    @Autowired
    private ProfesorRepository profesorRepository;

    public List<Profesor> obtenerProfesores(){

        return profesorRepository.findAll();
    }

    public Profesor guardarProfesor(Profesor profesor){

        return profesorRepository.save(profesor);
    }

    public List<Profesor> obtenerProfesoresByRol() {
        List<Profesor> listaEstudiantes = profesorRepository.findByRol("estudiante");
        if(listaEstudiantes.isEmpty()){
            throw new ProfesorExceptions(ProfesorType.NOT_PROFESOR_CONTENT);
        }
        return listaEstudiantes;
    }

    public boolean eliminarProfesor(String profesorId){
        if(profesorRepository.existsById(profesorId)){
            profesorRepository.deleteById(profesorId);
            return true;
        }
        return false;
    }


}
