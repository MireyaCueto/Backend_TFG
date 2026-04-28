package com.example.monumentos_backend.repository;

import com.example.monumentos_backend.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, String> {
}