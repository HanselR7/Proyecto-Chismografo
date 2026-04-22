package com.chismografo.Servicios;

import com.chismografo.Modelos.Tablon;
import com.chismografo.Repositorios.TablonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TablonServicio {
    @Autowired
    private TablonRepository tablonRepository;

    public List<Tablon> obtenerTodosTablones() {
        return tablonRepository.findAll();
    }

}
