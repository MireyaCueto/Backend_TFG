package com.example.monumentos_backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.monumentos_backend.model.Noticia;
import com.example.monumentos_backend.repository.NoticiaRepository;

@Service
public class NoticiaService {

    private final NoticiaRepository noticiaRepository;

    public NoticiaService(NoticiaRepository noticiaRepository) {
        this.noticiaRepository = noticiaRepository;
    }

    public Noticia save(Noticia noticia) {
        if (noticia.getId() == null) {
            noticia.setCreatedAt(LocalDateTime.now());
        }
        noticia.setLastModified(LocalDateTime.now());

        return noticiaRepository.save(noticia);
    }

    public List<Noticia> findAll() {
        return noticiaRepository.findAll();
    }

    public Optional<Noticia> getById(String id) {
        return noticiaRepository.findById(id);
    }

    public boolean existsById(String id) {
        return noticiaRepository.existsById(id);
    }

    public void deleteById(String id) {
        noticiaRepository.deleteById(id);
    }
}