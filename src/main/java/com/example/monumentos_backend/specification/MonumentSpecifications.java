package com.example.monumentos_backend.specification;

import com.example.monumentos_backend.model.Monument;
import com.example.monumentos_backend.model.Tag;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class MonumentSpecifications {

    // Specification<T> usa una función predicado, recibe tres elementos clave
    // del motor de búsqueda de Hibernate (JPA)
    public static Specification<Monument> hasName(String name) {
        // root: es la entidad "Monument", a raiz de esto se sacan los campos
        // query: se usa para búsquedas avanzadas en la BD (E.J. DISTINCT, ORDER BY...)
        // criteriaBuilder: contiene los métodos para comparar (E.J. equal, like, greaterThan...)
        return ((root, query, criteriaBuilder) -> {
           if (name == null || name.isBlank()) return null;

           String normalizedName = name.trim().toLowerCase();

           return criteriaBuilder.like(
                   criteriaBuilder.lower(root.get("name")),
                   "%" + normalizedName + "%"
           );
        });
    }

    public static Specification<Monument> hasTag(String tag) {
        return ((root, query, criteriaBuilder) -> {
            if (tag == null || tag.isBlank()) return null;

            String normalizedTag = tag.trim().toUpperCase();

            return criteriaBuilder.like(criteriaBuilder.upper(root.join("tag", JoinType.LEFT).get("name")),
                    "%" + normalizedTag + "%");
        });
    }

    public static Specification<Monument> isAccessible(Boolean accessibility) {
        return ((root, query, criteriaBuilder) -> {
            if (accessibility == null) return null;

            return criteriaBuilder.equal(root.get("accessibility"), accessibility);
        });
    }

    public static Specification<Monument> isActive(Boolean active) {
        return (((root, query, criteriaBuilder) -> {
            if (active == null) return null;

            return criteriaBuilder.equal(root.get("activate"), active);
        }));
    }
}
