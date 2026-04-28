package com.chismografo.Repositorios;
import com.chismografo.Modelos.Tutor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TutorRepository extends MongoRepository<Tutor, String> {
    List<Tutor> findByRol(String rol);
}
