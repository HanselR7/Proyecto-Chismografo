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

@RestController
@RequestMapping("/api/materia")
public class MateriaController {

    @Autowired
    private MateriaServicio materiaServicio;

    @GetMapping("/obtenerMaterias")
    public List<Materia> obtenerMaterias(){
        return materiaServicio.obtenerMaterias();
    }

    @PostMapping(value = "/guardarMateria", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> guardarMateria(@RequestBody String materiaSinIdJSON){
        ModelResponse<Materia> response = new ModelResponse<>();
        Gson gson = new Gson();
        try {
            Materia materiaSinId = gson.fromJson(materiaSinIdJSON, Materia.class);
            Materia newMateria = materiaServicio.guardarMateria(materiaSinId);

            response.setMensaje("Materia agregada con exito");
            response.setCodigo(500);
            response.setData(newMateria);

            Type type = new TypeToken<ModelResponse<Materia>>(){}.getType();
            return new  ResponseEntity<>(gson.toJson(response, type), HttpStatus.OK);

        }catch (Exception e){
            response.setMensaje("Errror al aguergar la materia" + e.getMessage());
            System.out.println("Errror al aguergar la materia" + e.getMessage());
            response.setCodigo(200);
            return new ResponseEntity<>(gson.toJson(response), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

