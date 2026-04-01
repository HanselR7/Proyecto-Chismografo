package com.chismografo.controladores;

import com.chismografo.Modelos.Tutor;
import com.chismografo.Servicios.TutorServicio;
import com.chismografo.utils.ModelResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.ArrayList;
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

    @GetMapping(value = "/obtenerTutoresPorRol", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> obtenerTutoresPorRol() {
        ModelResponse<List<Tutor>> response = new ModelResponse<>();
        Gson gson = new Gson();

        try {
            List<Tutor> lista = tutorServicio.obtenerTutoresPorRol();

            if (lista == null || lista.isEmpty()) {
                response.setMensaje("No se encontraron tutores.");
                response.setCodigo(204);
                response.setData(new ArrayList<>());
            } else {
                response.setMensaje("Tutores recuperados con éxito.");
                response.setCodigo(200);
                response.setData(lista);
            }

            Type type = new com.google.gson.reflect.TypeToken<ModelResponse<List<Tutor>>>(){}.getType();
            String jsonFinal = gson.toJson(response, type);

            return ResponseEntity.ok(jsonFinal);

        } catch (Exception e) {
            response.setMensaje("Error: " + e.getMessage());
            response.setCodigo(500);
            return new ResponseEntity<>(gson.toJson(response), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
