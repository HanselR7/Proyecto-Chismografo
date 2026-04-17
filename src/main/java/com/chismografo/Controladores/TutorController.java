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
import java.util.Map;

@RestController
@RequestMapping("/api/tutor")
public class TutorController {

    @Autowired
    private TutorServicio tutorServicio;

    @GetMapping("/obtenerTutoresPorRol")
    public ResponseEntity<ModelResponse> obtenerTutoresPorRol() {
        List<Tutor> lista = tutorServicio.obtenerTutoresPorRol().orElse(Collections.emptyList());

        if (lista.isEmpty()) {
            return ResponseEntity.status(204).body(
                    ModelResponse.builder()
                            .mensaje("No se encontraron tutores.")
                            .codigo(204)
                            .build());
        }

        return ResponseEntity.status(200).body(
                ModelResponse.builder()
                        .mensaje("Tutores disponibles")
                        .codigo(200)
                        .data(lista)
                        .build()
        );
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

    @DeleteMapping(value = "/eliminarTutor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> eliminarTutor(@RequestBody String tutorIdJSON) {
        ModelResponse<Tutor> response = new ModelResponse<>();
        Gson gson = new Gson();
        Type type = new TypeToken<ModelResponse<Tutor>>(){}.getType();
        try {
            Map<String, String> mapa = gson.fromJson(tutorIdJSON, new TypeToken<Map<String, String>>(){}.getType());
            String tutorId = mapa.get("id");

            if (tutorServicio.eliminarTutor(tutorId)) {
                response.setMensaje("Tutor eliminado con exito");
                response.setCodigo(200);
                return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.OK);
            }

            response.setMensaje("No se pudo borrar al tutor con id " + tutorId);
            System.out.println("No se pudo borrar al tutor con id " + tutorId);
            response.setCodigo(500);
            return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            response.setMensaje("Error al momento de eliminar al tutor: " + e.getMessage());
            System.out.println("Error al momento de eliminar al tutor: " + e.getMessage());
            response.setCodigo(500);
            return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
