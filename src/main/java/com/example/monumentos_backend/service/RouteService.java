package com.example.monumentos_backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.monumentos_backend.model.Monument;
import com.example.monumentos_backend.utils.GeoUtils;
import org.springframework.stereotype.Service;

import com.example.monumentos_backend.model.Ruta;
import com.example.monumentos_backend.repository.RouteRepository;

@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final MonumentRepository monumentRepository;

    public RouteService(RouteRepository routeRepository,MonumentRepository monumentRepository) {
        this.routeRepository = routeRepository;
        this.monumentRepository = monumentRepository;
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

    public Ruta save(Ruta route) {
        if (route.getMonuments() != null && !route.getMonuments().isEmpty()) {
            List<Monument> realMonuments = route.getMonuments().stream()
                    .map(m -> monumentRepository.findById(m.getId()).orElse(null))
                    .filter(m -> m != null)
                    .collect(Collectors.toList());
            route.setMonuments(realMonuments);
        }

        if(Boolean.TRUE.equals(route.getActivate()) && !route.canBeActive()){
            route.setActivate(false);
        }
        return routeRepository.save(route);
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
        return route;
    }

}
