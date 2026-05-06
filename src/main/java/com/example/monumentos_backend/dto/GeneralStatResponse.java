package com.example.monumentos_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GeneralStatResponse {
    private String name;
    private String period;
    private Long totalDownloads;

    @JsonProperty("average_score")
    private Double averageScore;
}