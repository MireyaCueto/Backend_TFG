package com.example.monumentos_backend.repository;

import com.example.monumentos_backend.model.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Ruta, String> {
}
