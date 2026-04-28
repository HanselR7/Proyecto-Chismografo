package com.chismografo.Controladores;

import com.chismografo.Modelos.Profesor;

import com.chismografo.Modelos.Tutor;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/profesor")
public class profeController {
   @Autowired
   private ProfesorServico profesorServico;



   @GetMapping(value = "/obtenerProfesores", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<ModelResponse<List<Profesor>>> obtenerEstudiantes() {
      List<Profesor> listaEstudiantes = profesorServico.obtenerProfesores();



      return ResponseEntity.ok(
              ModelResponse.<List<Profesor>>builder()
                      .mensaje("Estudiantes...")
                      .codigo(200)
                      .data(listaEstudiantes)
                      .build()
      );
   }



   @PostMapping(value = "/guardarProfesor", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<String> guardarProfesor(@RequestBody String profesorSinIDjSDN) {
      ModelResponse<Profesor> response = new ModelResponse<>();
      Gson gson = new Gson();
      try {
         Profesor profesorSinID = gson.fromJson(profesorSinIDjSDN, Profesor.class);
         profesorSinID.setRol("profe");

         Profesor profesorNuevo = profesorServico.guardarProfesor(profesorSinID);

         response.setMensaje("profesor agregado con exito : ");
         response.setCodigo(200);
         response.setData(profesorNuevo);

         Type type = new TypeToken<ModelResponse<Profesor>>() {
         }.getType();

         return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.OK);

      } catch (Exception e) {
         response.setMensaje("error al momento de guaradar el profe: " + e.getMessage());
         System.out.println("error al menonte de guardar profe: " + e.getMessage());
         response.setCodigo(500);
         return new ResponseEntity<>(gson.toJson(response), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @PostMapping(value = "/actualizarProfesor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<String> actualizarTutor(@RequestBody String profesorJSON) {
      ModelResponse<Profesor> response = new ModelResponse<>();
      Gson gson = new Gson();
      try {
         Profesor profesor = gson.fromJson(profesorJSON, Profesor.class);
         if (profesor.getId() == null) {
            response.setMensaje("id obligatorio");
            response.setCodigo(400);
            Type type = new TypeToken<ModelResponse<Profesor>>() {
            }.getType();
            return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.BAD_REQUEST);
         }
         Profesor profesorActualizado = profesorServico.guardarProfesor(profesor);
         response.setMensaje("tutor actualizado con exito");
         response.setCodigo(200);
         response.setData(profesorActualizado);
         Type type = new TypeToken<ModelResponse<Profesor>>() {
         }.getType();
         return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.OK);
      } catch (Exception e) {
         response.setMensaje("error al momento de actualizar el tutor: " + e.getMessage());
         System.out.println("error al momento de actualizar el tutor: " + e.getMessage());
         response.setCodigo(500);
         return new ResponseEntity<>(gson.toJson(response), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @DeleteMapping(value = "/eliminarProfesor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<String> eliminarProfesor(@RequestBody String profesorIdJSON) {
      ModelResponse<Profesor> response = new ModelResponse<>();
      Gson gson = new Gson();
      Type type = new TypeToken<ModelResponse<Profesor>>() {
      }.getType();
      try {

         Map<String, String> mapa = gson.fromJson(profesorIdJSON, new TypeToken<Map<String, String>>() {
         }.getType());
         String profesorId = mapa.get("id");

         if (profesorServico.eliminarProfesor(profesorId)) {

            response.setMensaje("tutor eliminado con exito");
            response.setCodigo(200);
            return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.OK);
         }
         response.setMensaje("NO SE ENCONTRO EL PROFESOR");
         System.out.println("NO SE ENCONTRO EL PROFESOR");
         response.setCodigo(204);
         return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.NO_CONTENT);
      } catch (Exception e) {
         response.setMensaje("error al momento de actualizar el tutor: " + e.getMessage());
         System.out.println("error al momento de actualizar el tutor: " + e.getMessage());
         response.setCodigo(500);
         return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @PostMapping("/guardarProfePro")
   public ResponseEntity<ModelResponse<Profesor>> guardarProfePro(@RequestBody Profesor profesor) {
      profesor.setRol("profesor");

      Profesor ProfesorNuevo = profesorServico.guardarProfesor(profesor);
      if (ProfesorNuevo == null) {
         return ResponseEntity.status(204).body(
                 ModelResponse.<Profesor>builder()
                         .mensaje("No se encontraron estudiantes")
                         .codigo(204)
                         .data(null)
                         .build()
         );
      }
      return ResponseEntity.status(200).body(
              ModelResponse.<Profesor>builder()
                      .mensaje((profesor.getId().equals("")) ? "profesor agregado" : "profesor actualizado")
                      .codigo(200)
                      .data(ProfesorNuevo)
                      .build()
      );
   }

//@DeleteMapping("eliminarMamalon")
   //  public ResponseEntity<ModelResponse<Boolean>> eliminar(@RequestBody )


   @DeleteMapping("/eliminarProfesorPro")
   public ResponseEntity<ModelResponse<Boolean>> eliminarProfesorPro(@RequestBody Profesor idProfesor) {
      if (profesorServico.eliminarProfesor(idProfesor.getId())) {
         return ResponseEntity.status(200).body(
                 ModelResponse.<Boolean>builder()
                         .mensaje("profesor eliminado")
                         .codigo(200)
                         .build()
         );
      }
      return ResponseEntity.status(204).body(
              ModelResponse.<Boolean>builder()
                      .mensaje("error xd")
                      .codigo(204)
                      .build()
      );
   }

}
