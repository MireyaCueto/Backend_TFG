package com.example.monumentos_backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "control")
@Getter
@Setter
@JsonPropertyOrder({
        "id",
        "name",
        "active",
        "created_at",
        "last_modified"
})
public class Control {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true, length = 40)
    @JsonProperty("name")
    private String name;

    @Column(name = "active")
    @JsonProperty("active")
    private Boolean active = true; // Por defecto a true

    @Column(name = "created_at", updatable = false)
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_modified")
    @JsonProperty("last_modified")
    private LocalDateTime lastModified;
}