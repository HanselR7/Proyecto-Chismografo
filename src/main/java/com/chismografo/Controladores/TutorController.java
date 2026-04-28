package com.chismografo.Controladores;

import com.chismografo.Modelos.Tutor;
import com.chismografo.Servicios.TutorServicio;
import com.chismografo.utils.ModelResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/tutor")
public class TutorController {

    @Autowired
    private TutorServicio tutorServicio;

    @GetMapping(value = "/obtenerTutores", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> obtenerTutores() {
        Gson gson = new Gson();
        try {
            List<Tutor> tutores = tutorServicio.obtenerTodosTutores();
            return ResponseEntity.ok(gson.toJson(tutores));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/obtenerTutoresPorRol")
    public ResponseEntity<ModelResponse<List<Tutor>>> obtenerTutoresPorRol() {
        List<Tutor> lista = tutorServicio.obtenerTutoresPorRol();

        return ResponseEntity.ok(
                ModelResponse.<List<Tutor>>builder()
                        .mensaje("Tutores disponibles")
                        .codigo(200)
                        .data(lista)
                        .build()
        );
    }

    @PostMapping(value = "/guardarTutor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> guardarTutor(@RequestBody String jsonEntrada) {
        ModelResponse<Tutor> response = new ModelResponse<>();
        Gson gson = new Gson();
        try {
            Tutor tutor = gson.fromJson(jsonEntrada, Tutor.class);
            tutor.setRol("tutor");
            Tutor nuevoTutor = tutorServicio.guardarTutor(tutor);

            response.setCodigo(200);
            response.setMensaje("Tutor guardado con exito");
            response.setData(nuevoTutor);

            Type type = new TypeToken<ModelResponse<Tutor>>(){}.getType();
            return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.CREATED);
        } catch (Exception e)  {
            response.setMensaje("Error al guardar tutor: " + e.getMessage());
            response.setCodigo(500);
            System.out.println("Error al guardar tutor: " + e.getMessage());
            return new ResponseEntity<>(gson.toJson(response), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
