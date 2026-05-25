package com.example.monumentos_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Table(name = "pictures")
@Getter @Setter
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String url;

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
