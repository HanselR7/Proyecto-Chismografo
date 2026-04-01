package com.chismografo.inicializadorMongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
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
            log.info(">>>> [MONGO-CHECK] Iniciando escaneo de clúster...");

            // 1. Listar todas las bases de datos disponibles para el usuario Hansel
            log.info(">>>> [MONGO-CHECK] Bases de datos encontradas:");
            for (String dbName : mongoClient.listDatabaseNames()) {
                log.info("   - BD: {}", dbName);
            }

            // 2. Entrar a la base específica y listar colecciones
            MongoDatabase db = mongoClient.getDatabase("Chismografo");
            log.info(">>>> [MONGO-CHECK] Explorando colecciones en 'Chismografo':");

            boolean existeUsuarios = false;
            for (String colName : db.listCollectionNames()) {
                log.info("   - Colección: {}", colName);
                if (colName.equalsIgnoreCase("usuarios")) {
                    existeUsuarios = true;
                    long count = db.getCollection(colName).countDocuments();
                    log.info("     -> Documentos encontrados en '{}': {}", colName, count);
                }
            }

            if (!existeUsuarios) {
                log.warn(">>>> [MONGO-CHECK] ⚠️ ¡ALERTA! La colección 'Usuarios' no aparece en la lista.");
            }

        } catch (Exception e) {
            log.error(">>>> [MONGO-CHECK] ❌ ERROR CRÍTICO: {}", e.getMessage());
        }
    }
}