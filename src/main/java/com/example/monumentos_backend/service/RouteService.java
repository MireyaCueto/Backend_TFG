package com.example.monumentos_backend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.monumentos_backend.model.Monument;
import com.example.monumentos_backend.specification.RouteSpecifications;
import com.example.monumentos_backend.utils.GeoUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.monumentos_backend.model.Ruta;
import com.example.monumentos_backend.model.Score;
import com.example.monumentos_backend.repository.MonumentRepository;
import com.example.monumentos_backend.repository.RouteRepository;
import com.example.monumentos_backend.repository.ScoreRepository;

@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final MonumentRepository monumentRepository;
    private final ScoreRepository scoreRepository;

    public RouteService(
            RouteRepository routeRepository,
            MonumentRepository monumentRepository,
            ScoreRepository scoreRepository) {
        this.routeRepository = routeRepository;
        this.monumentRepository = monumentRepository;
        this.scoreRepository = scoreRepository;
    }

    public List<Ruta> findAll() {
        return routeRepository.findAll().stream()
                .map(route -> this.calculateRouteStats(route))
                .collect(Collectors.toList());
    }

    public List<Ruta> findByFilters(
            String name,
            Boolean activate,
            String tag,
            String sortBy,
            String orderBy
    ) {
        Specification<Ruta> spec = Specification.where(RouteSpecifications.hasName(name)
                .and(RouteSpecifications.isActive(activate))
                .and(RouteSpecifications.hasTag(tag)));

        List<Ruta> rutasInmutables = routeRepository.findAll(spec).stream()
                .map(this::calculateRouteStats)
                .toList();

        List<Ruta> routes = new ArrayList<>(rutasInmutables);

        routes.sort(getRouteComparator(sortBy, orderBy));

        return routes;
    }

    public Optional<Ruta> getById(String id) {
        return routeRepository.findById(id)
                .map(route -> this.calculateRouteStats(route));
    }

    public boolean existsById(String id) {
        return routeRepository.existsById(id);
    }

    public Optional<Ruta> save(Ruta route) {
        if (route.getId() == null) {
            route.setCreatedAt(LocalDateTime.now());
        }
        route.setLastModified(LocalDateTime.now());

        if (route.getMonuments() != null) {
            List<Monument> realMonuments = route.getMonuments().stream()
                    .map(monument -> monumentRepository.findById(monument.getId()).orElse(null))
                    .filter(monument -> monument != null)
                    .collect(Collectors.toList());
            route.setMonuments(realMonuments);
        }

        if (Boolean.TRUE.equals(route.getActivate()) && !route.canBeActive()) {
            route.setActivate(false);
        }

        Ruta savedRoute = routeRepository.save(route);

        return getById(savedRoute.getId());
    }

    public void deleteById(String id) {
        routeRepository.deleteById(id);
    }

    // Funciones adicionales

    // Funciones de calcultos de datos especificos
    private Ruta calculateRouteStats(Ruta route) {
        Monument monumentoAnterior = null;
        // Suponiendo que la velocidad media de una persona haciendo turismo es de
        // 0,833333m/s
        double velocidadMediaTurismo = 0.833333;
        double distanciaTotalMetros = 0.0;
        double tiempoEstimado = 0.0;
        double averageScore = scoreRepository.getAverageScoreByRouteId(route.getId()) == null ? 0
                : scoreRepository.getAverageScoreByRouteId(route.getId());
        for (Monument monument : route.getMonuments()) {
            if (monument.getActivate()) {
                if (monumentoAnterior == null)
                    monumentoAnterior = monument;
                else {
                    distanciaTotalMetros += GeoUtils.teoremaHaversine(monument, monumentoAnterior);
                    monumentoAnterior = monument;
                }
            }
        }
        distanciaTotalMetros *= 1000;
        tiempoEstimado = distanciaTotalMetros / velocidadMediaTurismo;
        route.setTotalDistanceMeters(distanciaTotalMetros);
        route.setEstimatedTimeSeconds(tiempoEstimado);
        route.setAverageScore(averageScore != 0.0 ? averageScore : 0.0);

        return route;
    }

    private Comparator<Ruta> getRouteComparator(String sortBy, String orderBy) {
        boolean ascending = "asc".equalsIgnoreCase(orderBy);

        String normalizedSortBy = sortBy == null || sortBy.isBlank()
                ? "createdAt"
                : sortBy.trim().toLowerCase();

        return switch (normalizedSortBy) {
            case "difficult", "difficulty" -> Comparator.comparing(
                    Ruta::getDifficult,
                    nullableComparator(ascending)
            );

            case "averagescore", "average_score" -> Comparator.comparing(
                    Ruta::getAverageScore,
                    nullableComparator(ascending)
            );

            case "totaldistancemeters", "total_distance_meters" -> Comparator.comparing(
                    Ruta::getTotalDistanceMeters,
                    nullableComparator(ascending)
            );

            case "estimatedtimeseconds", "estimated_time_seconds" -> Comparator.comparing(
                    Ruta::getEstimatedTimeSeconds,
                    nullableComparator(ascending)
            );

            case "createdat", "created_at" -> Comparator.comparing(
                    Ruta::getCreatedAt,
                    nullableComparator(ascending)
            );

            default -> Comparator.comparing(
                    Ruta::getCreatedAt,
                    nullableComparator(false)
            );
        };
    }

    private static <T extends Comparable<? super T>> Comparator<T> nullableComparator(boolean ascending) {
        Comparator<T> comparator = ascending
                ? Comparator.naturalOrder()
                : Comparator.reverseOrder();

        return Comparator.nullsLast(comparator);
    }


    public Score saveScore(Score score) {
        return scoreRepository.save(score);
    }

}
