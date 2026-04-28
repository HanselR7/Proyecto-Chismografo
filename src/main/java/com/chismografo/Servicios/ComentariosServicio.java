package com.chismografo.Servicios;

import com.chismografo.Modelos.Comentarios;
import com.chismografo.Repositorios.ComentariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ComentariosServicio {

    @Autowired
    private ComentariosRepository comentariosRepository;

    public Comentarios guardarComentario(Comentarios comentarios){
        return comentariosRepository.save(comentarios);
    }





}
