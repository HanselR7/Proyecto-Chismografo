package com.chismografo.Repositorios;
import com.chismografo.Modelos.Estudiantes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//Esta clase funciona como una interfaz de base de datos, es decir que aqui ya iran las consultas directas (Las simples)
public interface EstudianteRepository extends MongoRepository<Estudiantes, String> {
    Optional<List<Estudiantes>> findByRol(String rol);
}
