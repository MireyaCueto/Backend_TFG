package com.example.monumentos_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "audios")
@Getter @Setter
public class Audio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String url;

    private Boolean kids;

    private String language;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "last_modified")
    private LocalDateTime lastModified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_monumento", nullable = false)
    @JsonIgnore
    private Monument monument;

    @PrePersist
    protected  void onCreate(){
        createdAt = LocalDateTime.now();
        lastModified = LocalDateTime.now();
    }

}
