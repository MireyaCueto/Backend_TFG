package com.example.monumentos_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "monumento")
@Getter
@Setter
public class Monument {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private Boolean activate;
    private Double lat;
    private Double lon;

    @Column(name = "accessibility")
    private Boolean accessibility;

    @Column(name = "maps_url")
    private String mapsUrl;

    @Column(name = "n_likes")
    private Integer nLikes;

    // Relación con el Tag
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    // Relaciones One-To-Many
    @OneToMany(mappedBy = "monument", cascade = CascadeType.ALL)
    private List<Description> descriptions;

    @OneToMany(mappedBy = "monument", cascade = CascadeType.ALL)
    private List<Picture> pictures;

    @OneToMany(mappedBy = "monument", cascade = CascadeType.ALL)
    private List<Audio> audios;

    // Relación Many-to-Many con Rutas a través de la tabla intermedia
    @ManyToMany
    @JoinTable(name = "rutes_monumentos", joinColumns = @JoinColumn(name = "id_monumento"), inverseJoinColumns = @JoinColumn(name = "id_rutes"))
    private List<Ruta> rutas;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "last_modified")
    private String lastModified;
}
