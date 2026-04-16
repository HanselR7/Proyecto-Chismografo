package com.chismografo.Servicios;

import com.chismografo.Modelos.Profesor;
import com.chismografo.Modelos.Tutor;
import com.chismografo.Repositorios.TutorRepository;
import com.chismografo.utils.ModelResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
//tipo controlador

@Service
public class TutorServicio {

    @Autowired
    private TutorRepository tutorRepository;

    public List<Tutor> obtenerTodosTutores() {
        return tutorRepository.findAll();
    }

    public List<Tutor> obtenerTutoresPorRol() {
        return tutorRepository.findByRol("tutor");
    }

   // public void guardarTutor(Tutor tutor) {
     //   return tutorRepository.save(tutor);
   // }


}
