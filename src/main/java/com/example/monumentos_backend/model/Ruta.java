package com.example.monumentos_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "rutes")
@Getter
@Setter
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer difficult;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_modified")
    private LocalDateTime lastModified;

    @ManyToMany(mappedBy = "rutas")
    @JsonIgnore
    private List<Monument> monuments;
}
