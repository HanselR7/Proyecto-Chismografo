package com.chismografo.Modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Tablon {
    private String grupoId;
    private String tipoAnuncio;
    private String tituloAnuncio;
    private String descripcionAnuncio;
    private Date fechaPublicacion;
    private String idUsuario;
    private List<String> idComentario;
}
