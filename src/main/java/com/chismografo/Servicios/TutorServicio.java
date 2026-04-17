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

    public Optional<List<Tutor>> obtenerTutoresPorRol() {
        return tutorRepository.findByRol("tutor");
    }

    public Tutor guardarTutor(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    public boolean eliminarTutor(String tutorId) {
        if (tutorRepository.existsById(tutorId)) {
            tutorRepository.deleteById(tutorId);
            return true;
        }
        return false;
    }
}
