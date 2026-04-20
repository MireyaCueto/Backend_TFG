package com.example.monumentos_backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.monumentos_backend.model.Ruta;
import com.example.monumentos_backend.repository.RouteRepository;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Ruta> findAll() {
        return routeRepository.findAll().stream()
                .map(route -> this.calculateRouteStats(route))
                .collect(Collectors.toList());
    }

    public Optional<Ruta> getById(String id) {
        return routeRepository.findById(id)
                .map(route -> this.calculateRouteStats(route));
    }

    // Funciones adicionales

    // Funciones de calcultos de datos especificos
    private Ruta calculateRouteStats(Ruta route) {
        route.setTotalDistanceMeters(1.0);
        route.setEstimatedTimeSeconds(1.0);
        return route;
    }
}
