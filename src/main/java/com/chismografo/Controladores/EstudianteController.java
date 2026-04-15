package com.chismografo.Controladores;

import com.chismografo.Modelos.Estudiantes;
import com.chismografo.Modelos.Tutor;
import com.chismografo.Servicios.EstudianteServicio;
import com.chismografo.utils.ModelResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/api/estudiante")
public class EstudianteController {


    @Autowired
    private EstudianteServicio estudianteServicio;

    @GetMapping(value = "/obtenerEstudiantes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> obtenerEstudiantes() {
        Gson gson = new Gson();
        try {
            List<Estudiantes> estudiantes = estudianteServicio.obtenerTodosEstudiantes();
            return ResponseEntity.ok(gson.toJson(estudiantes));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    /*produces = responder, cosume = consumir*/
    @PostMapping(value = "/guardarEstudiante", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    /*RequestBody = */
    public ResponseEntity<String> guardarEstudiante(@RequestBody String estdianteSinIDJSON) {
        ModelResponse<Estudiantes> response = new ModelResponse<>();
        Gson gson = new Gson();
        Type type = new TypeToken<ModelResponse<Estudiantes>>() {}.getType();
        try {
            Estudiantes estudianteSinID = gson.fromJson(estdianteSinIDJSON, Estudiantes.class);
            estudianteSinID.setRol("estudiante");
            Estudiantes estudiantesNuevo = estudianteServicio.guardarEstudiante(estudianteSinID);
            response.setMensaje("Estudiante guardado correctamente");
            response.setCodigo(200);
            response.setData(estudiantesNuevo);
            return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.OK);

        } catch (Exception e) {
            response.setMensaje("Error al guardar estudiante" + e.getMessage());
            System.out.println("Error al guardar estudiante" + e.getMessage());
            response.setCodigo(500);
            return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/actualizarEstudiante", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> actualizarEstudiante(@RequestBody String estudianteJSON) {
        ModelResponse<Estudiantes> response = new ModelResponse<>();
        Gson gson = new Gson();
        Type type = new TypeToken<ModelResponse<Estudiantes>>() {}.getType();
        try {
            Estudiantes estudiante = gson.fromJson(estudianteJSON, Estudiantes.class);
            if (estudiante.getId() == null) {
                response.setMensaje("ID obligatorio");
                response.setCodigo(400);
                        return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.BAD_REQUEST);
            }

            Estudiantes estudianteActualizado = estudianteServicio.guardarEstudiante(estudiante);
            response.setMensaje("Estudiante actualizado correctamente");
            response.setCodigo(200);
            response.setData(estudianteActualizado);

            return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.OK);
        } catch (Exception e) {
            response.setMensaje("Error al actualizar estudiante" + e.getMessage());
            System.out.println("Error al actualizar estudiante" + e.getMessage());
            response.setCodigo(500);
                return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
