package com.example.monumentos_backend.controller;

import com.example.monumentos_backend.model.Ruta;
import com.example.monumentos_backend.service.RouteService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    // EndPoints Publicos

    @GetMapping("/public/routes")
    public List<Ruta> getAllRoutes() {
        return routeService.findAll();
    }

    @GetMapping("/public/routes/{id}")
    public ResponseEntity<Ruta> getRouteById(@PathVariable String id) {
        Optional<Ruta> route = routeService.getById(id);
        return route.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
