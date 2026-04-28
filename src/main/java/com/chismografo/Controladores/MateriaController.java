package com.chismografo.Controladores;

import com.chismografo.Modelos.Materia;
import com.chismografo.Servicios.MateriaServicio;
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
import java.util.Map;

@RestController
@RequestMapping("/api/materia")
public class MateriaController {

    @Autowired
    private MateriaServicio materiaServicio;

    @GetMapping(value = "/obtenerMaterias", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> obtenerMaterias(){
        Gson gson = new Gson();
        ModelResponse<List<Materia>> response = new ModelResponse<>();
        Type type = new TypeToken<ModelResponse<List<Materia>>>() {}.getType();
        try {
            List<Materia> listaMaterias = materiaServicio.obtenerMaterias();
            response.setMensaje("Materias obtenidas");
            response.setCodigo(200);
            response.setData(listaMaterias);
            return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.OK);
           } catch (Exception e) {
            response.setMensaje("Error obtener las materias" + e.getMessage());
            System.out.println("Error obtener las materias" + e.getMessage());
            response.setCodigo(500);
            return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/obtenerMateriasByGrado")
    public ResponseEntity<ModelResponse<List<Materia>>> obtenerMateriasByGrado(@RequestBody Materia materia){
        List<Materia> materiaByGradoEncontrada = materiaServicio.obtenerMateriaByGrado(materia.getGradoEscolar());
        return ResponseEntity.status(200).body(
                ModelResponse.<List<Materia>>builder()
                        .mensaje("Materias Encontradas")
                        .codigo(200)
                        .data(materiaByGradoEncontrada)
                        .build()
        );
    }

    @PostMapping(value = "/guardarMateria", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> guardarMateria(@RequestBody String materiaSinIdJSON) {
        ModelResponse<Materia> response = new ModelResponse<>();
        Gson gson = new Gson();
        try {
            Materia materiaSinId = gson.fromJson(materiaSinIdJSON, Materia.class);
            Materia newMateria = materiaServicio.guardarMateria(materiaSinId);

            response.setMensaje("Materia agregada con exito");
            response.setCodigo(500);
            response.setData(newMateria);

            Type type = new TypeToken<ModelResponse<Materia>>() {
            }.getType();
            return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.OK);

        } catch (Exception e) {
            response.setMensaje("Errror al aguergar la materia" + e.getMessage());
            System.out.println("Errror al aguergar la materia" + e.getMessage());
            response.setCodigo(200);
            return new ResponseEntity<>(gson.toJson(response), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/actualizarMaterias", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> actualizarMateria(@RequestBody String materiaJson) {
        ModelResponse<Materia> response = new ModelResponse<>();
        Gson gson = new Gson();
        Type type = new TypeToken<ModelResponse<Materia>>() {
        }.getType();
        try {
            Materia materia = gson.fromJson(materiaJson, Materia.class);
            if (materia.getId() == null) {
                response.setMensaje("Error: se necestita de un ID para poder continuar");
                response.setCodigo(400);
                return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.BAD_REQUEST);
            }
            Materia materiaActualizada = materiaServicio.guardarMateria(materia);
            response.setMensaje("Materia actualizada con exito");
            response.setCodigo(200);
            response.setData(materiaActualizada);
            return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.OK);
        } catch (Exception e) {
            response.setMensaje("Error al actulaizar materia" + e.getMessage());
            System.out.println("Error al actulaizar materia" + e.getMessage());
            response.setCodigo(500);
            return new ResponseEntity<>(gson.toJson(response), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/eliminarMateria", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> eliminarMateria(@RequestBody String materiaByIdJson) {
        ModelResponse<Materia> response = new ModelResponse<>();
        Gson gson = new Gson();
        Type type = new TypeToken<ModelResponse<Materia>>() {}.getType();
        try {
            Map<String, String> mapa = gson.fromJson(materiaByIdJson, new TypeToken<Map<String, String>>(){}.getType());
            String materiaById = mapa.get("id");

            if (materiaServicio.elimiarMateria(materiaById)) {
                response.setMensaje("Materia eliminada con exito");
                response.setCodigo(200);
                return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.OK);
            }
            response.setMensaje("Error: se necestita de un ID valido para poder continuar con la eliminacion");
            response.setCodigo(400);
            return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setMensaje("Error al eliminar la materia" + e.getMessage());
            System.out.println("Error al eliminar la materia" + e.getMessage());
            response.setCodigo(500);
            return new ResponseEntity<>(gson.toJson(response, type), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteMat")
    public ResponseEntity<ModelResponse<Boolean>> elimiarMateria (@RequestBody Materia materiaEliminada){
        String id_materiaEliminada = materiaEliminada.getId();
        if (id_materiaEliminada.equals("")){
            return ResponseEntity.status(200).body(
                    ModelResponse.<Boolean>builder()
                            .mensaje("No se encontraron materias con el solicitado")
                            .codigo(204)
                            .data(null)
                            .build()
            );
        }
        materiaServicio.elimiarMateria(id_materiaEliminada);
        return ResponseEntity.status(200).body(
                ModelResponse.<Boolean>builder()
                        .mensaje("Materia Eliminada")
                        .codigo(200)
                        .build()
        );
    }

    @PostMapping("/GuardarMateriaNuevo")
    public ResponseEntity<ModelResponse<Materia>> guardarMateria (@RequestBody Materia materia){
        Materia materiaNueva = materiaServicio.guardarMateria(materia);
        if (materiaNueva == null){
            return ResponseEntity.status(204).body(
                    ModelResponse.<Materia>builder()
                            .mensaje("No se encontraron materias")
                            .codigo(204)
                            .data(null)
                            .build()
            );
        }

        return ResponseEntity.status(200).body(
                ModelResponse.<Materia>builder()
                        .mensaje((materia.getId() == null) ? "Materia Agregada" : "Materia Actualizada")
                        .codigo(200)
                        .data(materiaNueva)
                        .build()
        );
    }

}
