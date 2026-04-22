package com.chismografo.Repositorios;

import com.chismografo.Modelos.Tablon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TablonRepository extends MongoRepository<Tablon,String> {
}
