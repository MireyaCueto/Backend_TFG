package com.example.monumentos_backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "localidades")
@Getter
@Setter
@JsonPropertyOrder({
        "id",
        "name",
        "provincia",
        "codigo_postal",
        "created_at",
        "last_modified"
})
public class Localidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Sigue siendo IDENTITY por el SERIAL
    private Integer id;

    // Cambiado a "name" para coincidir con tu SQL
    @Column(name = "name", nullable = false)
    @JsonProperty("name")
    private String name;

    // Nuevos campos añadidos
    @Column(name = "provincia")
    @JsonProperty("provincia")
    private String provincia;

    @Column(name = "codigo_postal")
    @JsonProperty("codigo_postal")
    private Integer codigoPostal;

    @Column(name = "created_at", updatable = false)
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_modified")
    @JsonProperty("last_modified")
    private LocalDateTime lastModified;
}