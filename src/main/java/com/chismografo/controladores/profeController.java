package com.chismografo.controladores;

import com.chismografo.Modelos.Profesor;
import com.chismografo.Servicios.ProfesorServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tutor")
public class profeController {
   @Autowired
   private ProfesorServico profesorServico;

   @GetMapping ("/obtenerProfesores")
    public List<Profesor> obtenerProfesores(){

       return profesorServico.obetenerTutores() ;
   }
}
