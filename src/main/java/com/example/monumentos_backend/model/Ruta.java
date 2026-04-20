package com.example.monumentos_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "rutes")
@Getter
@Setter
@JsonPropertyOrder({
        "id",
        "name",
        "description",
        "difficult",
        "monuments",
        "tag",
        "total_distance_meters",
        "estimated_time_seconds",

        "created_at",
        "last_update"
})

public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @JsonProperty("name")
    private String name;

    @Column(columnDefinition = "TEXT")
    @JsonProperty("description")
    private String description;

    @JsonProperty("difficult")
    private Integer difficult;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "rutes_monumentos", joinColumns = @JoinColumn(name = "id_rutes"), inverseJoinColumns = @JoinColumn(name = "id_monumento"))
    private List<Monument> monuments;

    // 2. Creamos un método que devuelva solo los IDs
    @JsonProperty("monuments")
    public List<String> getMonumentsIds() {
        if (this.monuments == null) {
            return Collections.emptyList();
        }
        return this.monuments.stream()
                .map(Monument::getId)
                .collect(Collectors.toList());
    }

    @JsonProperty("tag")
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Transient
    @JsonProperty("total_distance_meters")
    private Double totalDistanceMeters;

    @Transient
    @JsonProperty("estimated_time_seconds")
    private Double estimatedTimeSeconds;

    @Column(name = "created_at")
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_modified")
    @JsonProperty("last_modified")
    private LocalDateTime lastModified;

}
