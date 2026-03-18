package com.chismografo.inicializadorMongo;

import com.mongodb.client.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@Configuration
public class MongoDBTestRunner {
    private static final Logger log = LoggerFactory.getLogger(MongoDBTestRunner.class);

    @Autowired
    private MongoClient mongoClient;

    @EventListener(ContextRefreshedEvent.class)
    public void verificarConexion() {
        try {
            log.info(">>>> [MONGO-CHECK] Verificando conexión a MongoDB Atlas...");
            mongoClient.listDatabaseNames().first();
            log.info(">>>> [MONGO-CHECK] ✅ ¡Conexión exitosa!");
        } catch (Exception e) {
            log.error(">>>> [MONGO-CHECK] ❌ ERROR: {}", e.getMessage());
        }
    }
}