package com.example.monumentos_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Entity
@Table(name = "description")
@Getter @Setter
public class Description {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String contenido;
    private Boolean complete;
    private Boolean kids;
    private String language;

    @ManyToOne
    @JoinColumn(name = "id_monumento")
    @JsonIgnore
    private Monument monument;
}
