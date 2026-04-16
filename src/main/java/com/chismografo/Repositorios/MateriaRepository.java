package com.chismografo.Repositorios;

import com.chismografo.Modelos.Materia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository //Generador de consultas
public interface MateriaRepository extends MongoRepository<Materia, String> {
}
