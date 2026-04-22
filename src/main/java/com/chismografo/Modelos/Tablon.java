package com.chismografo.Modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "tablon")
public class Tablon {
    @Id
    private String idTablon;
    private String grupoId;
    private String tipoAnuncio;
    private String tituloAnuncio;
    private String descripcionAnuncio;
    private Date fechaPublicacion;
    private String idUsuario;
    private List<String> idComentario;
}
