package com.chismografo.Servicios;

import com.chismografo.Exceptions.ProfesorExceptions;
import com.chismografo.Exceptions.ProfesorType;
import com.chismografo.Modelos.Profesor;
import com.chismografo.Repositorios.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorServico {

    @Autowired
    private ProfesorRepository profesorRepository;

    public Optional<List<Profesor>> obtenerProfesores(){
        return profesorRepository.findAll();
    }

    public Profesor guardarProfesor(Profesor profesor){
        return profesorRepository.save(profesor);
    }

    public Optional<List<Profesor>> obtenerProfesoresByRol(){
     List<Profesor> lista = profesorRepository.findByRol("profesor");
     if(lista.isEmpty()){
         throw new ProfesorExceptions(ProfesorType.NOT_PROFESOR_CONTENT);
     }
    }

    public boolean eliminarProfesor(String profesorId){
        if(profesorRepository.existsById(profesorId)){
            profesorRepository.deleteById(profesorId);
            return true;
        }
        return false;
    }


}
