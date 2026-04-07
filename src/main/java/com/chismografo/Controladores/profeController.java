package com.chismografo.Controladores;

import com.chismografo.Modelos.Profesor;

import com.chismografo.Servicios.ProfesorServico;

import com.chismografo.utils.ModelResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/api/profesor")
public class profeController {
   @Autowired
   private ProfesorServico profesorServico;

   @GetMapping (value = "/obtenerProfesores", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Profesor> obtenerProfesores(){

       return profesorServico.obetenerTutores() ;
   }
   @PostMapping(value ="/guardarProfesor", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<String> guardarProfesor(@RequestBody String profesorSinIDjSDN){
      ModelResponse<Profesor> response = new ModelResponse<>();
      Gson gson = new Gson();
      try{
         Profesor profesorSinID = gson.fromJson(profesorSinIDjSDN, Profesor.class);
         profesorSinID.setRol("profe");

         Profesor profesorNuevo = profesorServico.guardarProfesor(profesorSinID);

         response.setMensaje("profesor agregado con exito : " );
         response.setCodigo(200);
         response.setData(profesorNuevo);

         Type type = new TypeToken<ModelResponse<Profesor>>(){}.getType();

         return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.OK);

      } catch (Exception e) {
        response.setMensaje("error al momento de guaradar el profe: " + e.getMessage()) ;
         System.out.println("error al menonte de guardar profe: " + e.getMessage()) ;
         response.setCodigo(500);
         return new ResponseEntity<>(gson.toJson(response), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

}
