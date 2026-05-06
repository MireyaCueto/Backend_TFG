package com.example.monumentos_backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "stats")
@Getter
@Setter
public class Stat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_service", nullable = false, length = 40)
    @JsonProperty("name_service")
    private String nameService;

    @Column(name = "n_downloads")
    @JsonProperty("n_downloads")
    private Integer nDownloads = 0;

    @Column(name = "regis_date")
    @JsonProperty("regis_date")
    private LocalDate regisDate;
}