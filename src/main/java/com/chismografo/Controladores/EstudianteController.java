package com.chismografo.Controladores;

import com.chismografo.Modelos.Estudiantes;
import com.chismografo.Servicios.EstudianteServicio;
import com.chismografo.utils.ModelResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/api/estudiante")
public class EstudianteController {


    @Autowired
    private EstudianteServicio estudianteServicio;

    @GetMapping("/obtenerEstduaintes")
    public List<Estudiantes> obtenerEstudiantes(){
        return estudianteServicio.obtenerTodosEstudiantes();
    }

    /*produces = responder, cosume = consumir*/
    @PostMapping(value = "/guardarEstudiante", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    /*RequestBody = */
    public ResponseEntity<String> guardarEstudiante(@RequestBody String estdianteSinIDJSON){
        ModelResponse<Estudiantes> response = new ModelResponse<>();
        Gson gson = new Gson();

        try{
            Estudiantes estudianteSinID = gson.fromJson(estdianteSinIDJSON, Estudiantes.class);
            Estudiantes estudiantesNuevo = estudianteServicio.guardarEstudiante(estudianteSinID);
            
            response.setMensaje("Estudiante guardado correctamente");
            response.setCodigo(200);
            response.setData(estudiantesNuevo);

            Type type = new TypeToken<ModelResponse<Estudiantes>>(){}.getType();
            return new ResponseEntity<>(gson.toJson(response), HttpStatus.OK);
        }catch (Exception e){
            response.setMensaje("Error al guardar estudiante" + e.getMessage());
            System.out.println("Error al guardar estudiante" + e.getMessage());
            response.setCodigo(500);
            return new  ResponseEntity<>(gson.toJson(response), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
