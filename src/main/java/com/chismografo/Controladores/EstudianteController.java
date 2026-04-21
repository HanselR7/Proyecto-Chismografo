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
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/estudiante")
public class EstudianteController {


    @Autowired
    private EstudianteServicio estudianteServicio;

    @GetMapping(value = "/obtenerEstudiantes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModelResponse> obtenerEstudiantes() {
        List<Estudiantes> listaEstudiantes = estudianteServicio.obtenerEstudiantesByRol().orElse(Collections.emptyList());

        if(listaEstudiantes.isEmpty()){
            return ResponseEntity.status(204).body(
                    ModelResponse.builder()
                            .mensaje("No se encontraron estudiantes")
                            .codigo(204)
                            .build());
        }

        return  ResponseEntity.status(200).body(
                ModelResponse.builder()
                        .mensaje("Estudiantes...")
                        .codigo(200)
                        .data(listaEstudiantes)
                        .build()
        );
    }

    /*produces = responder, cosume = consumir*/
    @PostMapping(value = "/guardarEstudiante", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    /*RequestBody = */
    public ResponseEntity<String> guardarEstudiante(@RequestBody String estdianteSinIDJSON) {
        ModelResponse<Estudiantes> response = new ModelResponse<>();
        Gson gson = new Gson();
        Type type = new TypeToken<ModelResponse<Estudiantes>>() {
        }.getType();
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
        Type type = new TypeToken<ModelResponse<Estudiantes>>() {
        }.getType();
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

    @DeleteMapping(value = "/eliminarEstudiante", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> eliminarEstudiante(@RequestBody String estudianteIdJSON) {
        ModelResponse<Estudiantes> response = new ModelResponse<>();
        Gson gson = new Gson();
        Type type = new TypeToken<ModelResponse<Estudiantes>>() {}.getType();
        try {
            Map<String, String> mapa = gson.fromJson(estudianteIdJSON, new TypeToken<Map<String, String>>(){}.getType());
            String estudianteId = mapa.get("id");
            if (estudianteServicio.eliminarEstudiante(estudianteId)) {
                response.setMensaje("Estudiante eliminado correctamente");
                response.setCodigo(200);
                return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.OK);
            }
            response.setMensaje("No se pudo eliminar al estudiante con id: " + estudianteId);
            System.out.println("No se pudo eliminar al estudiante con id: " + estudianteId);
            response.setCodigo(500);
            return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            response.setMensaje("Error al momento de eliminar al estudiante" + e.getMessage());
            System.out.println("Error al momento de eliminar al estudiante" + e.getMessage());
            response.setCodigo(500);
            return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
