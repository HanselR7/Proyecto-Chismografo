package com.chismografo.Servicios;

import com.chismografo.Modelos.Grupo;
import com.chismografo.Repositorios.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoServicio {
    @Autowired
    private GrupoRepository GrupoRepository;
}
