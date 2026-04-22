package com.chismografo.Repositorios;

import com.chismografo.Modelos.Grupo;
import com.chismografo.Modelos.Materia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //Generador de consultas
public interface GrupoRepository extends MongoRepository<Grupo, String> {

}
