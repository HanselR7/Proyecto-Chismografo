package com.chismografo.Repositorios;

import com.chismografo.Modelos.Profesor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProfesorRepository extends MongoRepository<Profesor, String> {


}
