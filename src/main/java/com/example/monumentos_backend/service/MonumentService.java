package com.example.monumentos_backend.service;

import com.example.monumentos_backend.model.Monument;
import com.example.monumentos_backend.repository.MonumentRepository;
import com.example.monumentos_backend.specification.MonumentSpecifications;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public List<Monument> findByFilters(
            String name,
            String tag,
            Boolean accessibility,
            Boolean activate,
            String orderBy) {

        // Combinamos las búsquedas con .and()
        Specification<Monument> spec = Specification.where(MonumentSpecifications.hasName(name))
                .and(MonumentSpecifications.hasTag(tag))
                .and(MonumentSpecifications.isAccessible(accessibility))
                .and(MonumentSpecifications.isActive(activate));

        Sort sort = Sort.unsorted();

        if (orderBy != null && !orderBy.isBlank()) {
            Sort.Direction direction = "asc".equalsIgnoreCase(orderBy)
                    ? Sort.Direction.ASC
                    : Sort.Direction.DESC;

            sort = Sort.by(direction, "nLikes");
        }

        return monumentRepository.findAll(spec, sort);
    }

    public Monument save(Monument monument) {
        return monumentRepository.save(monument);
    }

    public List<Monument> findAll() {
        return monumentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Monument> getById(String id) {
        // return monumentRepository.findById(UUID.fromString(id));
        return monumentRepository.findById(id);
    }
    
    public Monument activateMonument(String id) {
        Monument monumento = monumentRepository.findById(id).orElse(null);
        if (monumento == null) {
            return null;
        }
        monumento.setActivate(!monumento.getActivate());
        return monumentRepository.save(monumento);
    }
    
    public void deleteById(String id) {
        monumentRepository.deleteById(id);
    }
}
