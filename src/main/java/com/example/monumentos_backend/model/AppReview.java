package com.example.monumentos_backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "app_reviews")
@Getter
@Setter
public class AppReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_device", nullable = false)
    @JsonProperty("id_device")
    private String idDevice;

    @Column(name = "name_service", nullable = false, length = 40)
    @JsonProperty("name_service")
    private String nameService;

    @Column(name = "score")
    private Integer score = 0;

    @Column(name = "created_at", updatable = false)
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
}