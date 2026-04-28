package com.chismografo.Repositorios;

import com.chismografo.Modelos.Profesor;
import com.chismografo.Modelos.Tutor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProfesorRepository extends MongoRepository<Profesor, String> {

    Optional<List<Profesor>> findByRol(String rol);
}
