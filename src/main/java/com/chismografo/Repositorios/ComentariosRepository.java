package com.chismografo.Repositorios;

import com.chismografo.Modelos.Comentarios;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComentariosRepository extends MongoRepository<Comentarios, String> {
}
