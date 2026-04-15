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

    @PostMapping(value = "/guardarTutor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> guardarTutor(@RequestBody String tutorSinIDJSON) {
        ModelResponse<Tutor> response = new ModelResponse<>();
        Gson gson = new Gson();

        try {
            Tutor tutorSinID = gson.fromJson(tutorSinIDJSON, Tutor.class);
            tutorSinID.setRol("tutor");

            Tutor tutorNuevo = tutorServicio.guardarTutor(tutorSinID);

            response.setMensaje("Tutor agregado con exito");
            response.setCodigo(200);
            response.setData(tutorNuevo);

            Type type = new TypeToken<ModelResponse<Tutor>>(){}.getType();
            return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.OK);
        } catch (Exception e) {
            response.setMensaje("Error al momento de guardar al tutor: " + e.getMessage());
            System.out.println("Error al momento de guardar al tutor: " + e.getMessage());
            response.setCodigo(500);
            return new ResponseEntity<>(gson.toJson(response), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/actualizarTutor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> actualizarTutor(@RequestBody String tutorJSON) {
        ModelResponse<Tutor> response = new ModelResponse<>();
        Gson gson = new Gson();

        try {
            Tutor tutor = gson.fromJson(tutorJSON, Tutor.class);
            if (tutor.getId() == null) {
                response.setMensaje("ID Obligatorio");
                response.setCodigo(400);
                Type type = new TypeToken<ModelResponse<Tutor>>(){}.getType();
                return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.BAD_REQUEST);
            }

            Tutor actualizado = tutorServicio.guardarTutor(tutor);
            response.setMensaje("Tutor actualizado con exito");
            response.setCodigo(200);
            response.setData(actualizado);

            Type type = new TypeToken<ModelResponse<Tutor>>(){}.getType();
            return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.OK);
        } catch (Exception e ) {
            response.setMensaje("Error al momento de actualizar al tutor: " + e.getMessage());
            System.out.println("Error al momento de actualizar al tutor: " + e.getMessage());
            response.setCodigo(500);
            return new ResponseEntity<>(gson.toJson(response), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
