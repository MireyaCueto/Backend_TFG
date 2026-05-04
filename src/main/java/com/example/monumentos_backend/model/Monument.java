package com.example.monumentos_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@JsonAutoDetect
@Table(name = "monumento")
@Getter
@Setter
@JsonPropertyOrder({
        "id",
        "name",
        "coordenates",
        "accessibility",
        "isActive",
        "tag",
        "maps_url",
        "NLikes",
        "description",
        "picture",
        "audio",
        "created_at",
        "last_update"
})
public class Monument {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @JsonProperty("isActive")
    @Column(name = "activate")
    private Boolean activate;
    @JsonIgnore
    private Double lat;
    @JsonIgnore
    private Double lon;

    @JsonProperty("coordenates")
    public Map<String, Double> getCoordenates() {
        Map<String, Double> coords = new HashMap<>();
        coords.put("lat", this.lat);
        coords.put("lon", this.lon);
        return coords;
    }

    @JsonProperty("accessibility")
    @Column(name = "accessibility")
    private Boolean accessibility;

    @JsonProperty("maps_url")
    @Column(name = "maps_url")
    private String mapsUrl;

    @JsonProperty("NLikes")
    @Column(name = "n_likes")
    private Integer nLikes;

    // Relación con el Tag
    @JsonProperty("tag")
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    // Relaciones One-To-Many
    @JsonProperty("description")
    @OneToMany(mappedBy = "monument", cascade = CascadeType.ALL)
    private List<Description> descriptions;

    @JsonProperty("picture")
    @OneToMany(mappedBy = "monument", cascade = CascadeType.ALL)
    private List<Picture> pictures;

    @JsonProperty("audio")
    @OneToMany(mappedBy = "monument", cascade = CascadeType.ALL)
    private List<Audio> audios;

    @JsonProperty("created_at")
    @Column(name = "created_at")
    private String createdAt;

    @JsonProperty("last_modified")
    @Column(name = "last_modified")
    private String lastModified;

    // Devuelve cierto si el monumento puede estar activo
    public boolean canBeActive() {
        return Boolean.TRUE.equals(this.activate);
    }
}
