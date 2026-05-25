package com.example.monumentos_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tags")
@Getter
@Setter
@JsonPropertyOrder({
        "id",
        "name",
        "colorHex",
})
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    @JsonProperty("name")
    private String name;

    @Column(name = "color_hex")
    @JsonProperty("colorHex")
    private String colorHex;

    @Column(name = "created_at", updatable = false)
    private String createdAt;

    @Column(name = "last_modified")
    @JsonIgnore
    private String lastModified;
}
