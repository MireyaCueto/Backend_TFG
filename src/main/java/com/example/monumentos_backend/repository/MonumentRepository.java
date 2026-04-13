package com.example.monumentos_backend.repository;

import com.example.monumentos_backend.model.Monument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MonumentRepository extends JpaRepository<Monument, UUID> {
}
