package com.example.monumentos_backend.repository;

import com.example.monumentos_backend.model.Control;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ControlRepository extends JpaRepository<Control, Integer> {
    // Spring Boot crea la consulta SQL automáticamente gracias a este nombre
    Optional<Control> findByName(String name);
}