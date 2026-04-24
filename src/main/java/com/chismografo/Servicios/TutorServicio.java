package com.chismografo.Servicios;

import com.chismografo.Excepciones.TutorError;
import com.chismografo.Excepciones.TutorErrorType;
import com.chismografo.Modelos.Tutor;
import com.chismografo.Repositorios.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorServicio {

    @Autowired
    private TutorRepository tutorRepository;

    public List<Tutor> obtenerTutoresPorRol() {
        List<Tutor> tutores = tutorRepository.findByRol("tutor");
        if (tutores.isEmpty()) {
            throw new TutorError(TutorErrorType.NOT_TUTOR_CONTENT);
        }
        return tutores;
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
