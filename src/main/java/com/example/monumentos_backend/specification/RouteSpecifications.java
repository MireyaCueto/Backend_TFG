package com.example.monumentos_backend.specification;

import com.example.monumentos_backend.model.Ruta;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class RouteSpecifications {

    public static Specification<Ruta> hasName(String name) {
        return ((root, query, criteriaBuilder) -> {
            if (name == null || name.isBlank()) return null;

            String normalizedName = name.trim().toLowerCase();

            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")),
                    "%" + normalizedName + "%"
            );
        });
    }

    public static Specification<Ruta> isActive(Boolean active) {
        return ((root, query, criteriaBuilder) -> {
           if (active == null) return null;

           return criteriaBuilder.equal(root.get("active"), active);
        });
    }

    public static Specification<Ruta> hasTag(String tag) {
        return ((root, query, criteriaBuilder) -> {
            if (tag == null || tag.isBlank()) return null;

            String normalizedTag = tag.trim().toUpperCase();

            try {
                Integer tagId = Integer.parseInt(normalizedTag.trim());
                return criteriaBuilder.equal(root.join("tag", JoinType.LEFT).get("id"), tagId);

            } catch (NumberFormatException e) {
                return null;
            }
        });
    }
}
