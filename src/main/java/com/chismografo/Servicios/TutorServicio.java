package com.chismografo.Servicios;

import com.chismografo.Modelos.Tutor;
import com.chismografo.Repositorios.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorServicio {

    @Autowired
    private TutorRepository tutorRepository;

    public List<Tutor> obtenerTodosTutores() {
        return tutorRepository.findAll();
    }

    public Optional<List<Tutor>> obtenerTutoresPorRol() {
        return tutorRepository.findByRol("tutor");
    }

    public Tutor guardarTutor(Tutor tutor) {
        return tutorRepository.save(tutor);
    }
}
