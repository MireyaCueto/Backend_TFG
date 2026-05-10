package com.example.monumentos_backend.dto;

import com.example.monumentos_backend.model.Monument;
import com.example.monumentos_backend.model.Picture;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@JsonPropertyOrder({ "id", "name", "coordenates", "pictures" })
public class MonumentSummary {

    private final String id;
    private final String name;
    private final Map<String, Double> coordenates;
    private final List<Picture> pictures;

    public MonumentSummary(Monument monument) {
        this.id = monument.getId();
        this.name = monument.getName();
        this.coordenates = monument.getCoordenates();
        this.pictures = monument.getPictures() != null
                ? monument.getPictures().stream()
                .sorted(Comparator.comparing(
                        Picture::getId,
                        Comparator.nullsLast(Comparator.naturalOrder())
                ))
                .collect(Collectors.toList())
                : Collections.emptyList();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @JsonProperty("coordenates")
    public Map<String, Double> getCoordenates() {
        return coordenates;
    }

    @JsonProperty("pictures")
    public List<Picture> getPictures() {
        return pictures;
    }
}