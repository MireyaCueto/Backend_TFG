package com.example.monumentos_backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "noticias")
@Getter
@Setter
@JsonPropertyOrder({
        "id",
        "titulo",
        "subtitulo",
        "contenido",
        "estado",
        "fecha_publicacion",
        "imagen_url",
        "created_at",
        "last_modified"
})
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "titulo")
    @JsonProperty("titulo")
    private String title;

    @Column(name = "subtitulo")
    @JsonProperty("subtitulo")
    private String subtitulo;

    // Añadido columnDefinition = "TEXT" para soportar noticias largas
    @Column(name = "contenido", columnDefinition = "TEXT")
    @JsonProperty("contenido")
    private String contenido;

    @Column(name = "estado")
    @JsonProperty("estado")
    private Integer estado;

    /*
     * - 0 Borrador
     * - 1 Desactivada
     * - 2 Publicada
     */

    @Column(name = "fecha_publicacion")
    @JsonProperty("fecha_publicacion")
    private LocalDateTime fecha_publicacion;

    // Añadido columnDefinition = "TEXT" para soportar URLs largas
    @Column(name = "imagen_url", columnDefinition = "TEXT")
    @JsonProperty("imagen_url")
    private String imagenUrl;

    // Añadido el mapeo y el updatable = false para que no se borre al hacer un PUT
    @Column(name = "created_at", updatable = false)
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    // Añadido el mapeo a la base de datos
    @Column(name = "last_modified")
    @JsonProperty("last_modified")
    private LocalDateTime lastModified;
}