package com.example.monumentos_backend.service;

import com.example.monumentos_backend.model.Monument;
import com.example.monumentos_backend.repository.MonumentRepository;
import com.example.monumentos_backend.specification.MonumentSpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonumentService {

    private final MonumentRepository monumentRepository;

    public MonumentService(MonumentRepository monumentRepository) {
        this.monumentRepository = monumentRepository;
    }

    // Busca aplicando los filtros dinámicos
    // Si los parámetros son nulos, ignora ese filtro
    public List<Monument> findByFilters(String name, String tag, Boolean accessibility, Boolean activate) {
        // Combinamos las búsquedas con .and()
        Specification<Monument> spec = Specification.where(MonumentSpecifications.hasName(name))
                .and(MonumentSpecifications.hasTag(tag))
                .and(MonumentSpecifications.isAccessible(accessibility))
                .and(MonumentSpecifications.isActive(activate));

        return monumentRepository.findAll(spec);
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
        monumentRepository.deleteById(id);
    }
}
