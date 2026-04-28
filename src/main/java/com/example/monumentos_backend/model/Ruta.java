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
        "isActive",
        "difficult",
        "monuments",
        "tag",
        "average_score",
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

    @Column(name = "active")
    @JsonProperty("isActive")
    private Boolean activate;

    @JsonProperty("difficult")
    private Integer difficult;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "rutes_monumentos", joinColumns = @JoinColumn(name = "id_rutes"), inverseJoinColumns = @JoinColumn(name = "id_monumento"))
    private List<Monument> monuments;

    @JsonProperty("tag")
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Transient
    @JsonProperty("average_score")
    private Double averageScore;

    @Transient
    @JsonProperty("total_distance_meters")
    private Double totalDistanceMeters;

    @Transient
    @JsonProperty("estimated_time_seconds")
    private Double estimatedTimeSeconds;

    @Column(name = "created_at", updatable = false)
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_modified")
    @JsonProperty("last_modified")
    private LocalDateTime lastModified;

    // Comprobamos si la ruta tiene algún monumento activo para que pueda ser
    // activada
    public boolean canBeActive() {
        if (this.monuments == null || this.monuments.isEmpty()) {
            return false;
        }

        return this.monuments.stream().anyMatch(Monument::canBeActive);
    }

    @JsonProperty("monuments")
    public void setMonumentsIds(List<String> ids) {
        if (ids != null) {
            this.monuments = ids.stream().map(id -> {
                Monument monument = new Monument();
                monument.setId(id);
                return monument;
            }).collect(Collectors.toList());
        }
    }

    @JsonProperty("monuments")
    public List<String> getMonumentsIds() {
        if (this.monuments == null) {
            return Collections.emptyList();
        }
        return this.monuments.stream()
                .map(Monument::getId)
                .collect(Collectors.toList());
    }
}
