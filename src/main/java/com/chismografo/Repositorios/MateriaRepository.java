package com.chismografo.Repositorios;

import com.chismografo.Modelos.Materia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //Generador de consultas
public interface MateriaRepository extends MongoRepository<Materia, String> {

    List<Materia> findByGradoEscolar(Integer grado);
}
