package com.example.monumentos_backend.service;

import com.example.monumentos_backend.model.Monument;
import com.example.monumentos_backend.repository.MonumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MonumentService {

    private final MonumentRepository monumentRepository;

    public MonumentService(MonumentRepository monumentRepository) {
        this.monumentRepository = monumentRepository;
    }

    public Monument save(Monument monument) {
        return monumentRepository.save(monument);
    }

    public List<Monument> findAll() {
        return monumentRepository.findAll();
    }

    public Optional<Monument> getById(String id) {
        // return monumentRepository.findById(UUID.fromString(id));
        return monumentRepository.findById(id);
    }

    public void deleteById(String id) {
        monumentRepository.deleteById(UUID.fromString(id));
    }
}
