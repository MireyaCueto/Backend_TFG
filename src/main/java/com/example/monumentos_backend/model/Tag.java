package com.example.monumentos_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tags")
@Getter @Setter
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "color_hex")
    private String colorHex;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "last_modified")
    private String lastModified;
}
