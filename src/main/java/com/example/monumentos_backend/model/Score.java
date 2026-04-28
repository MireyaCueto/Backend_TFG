package com.example.monumentos_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "score")
@IdClass(ScoreId.class)
@Getter
@Setter
public class Score {
    @Id
    @Column(name = "id_device")
    private String id;

    @Column(name = "id_rutes")
    private String routeId;

    private Double score;
}