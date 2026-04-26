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

    @GetMapping("/public/route")
    public List<Ruta> getAllRoutes() {
        return routeService.findAll();
    }

    @GetMapping("/public/route/{id}")
    public ResponseEntity<Ruta> getRouteById(@PathVariable String id) {
        Optional<Ruta> route = routeService.getById(id);
        return route.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoints Privados

    @PostMapping("/admin/route")
    public ResponseEntity<Ruta> saveRoute(@RequestBody Ruta route) {
        return ResponseEntity.ok(routeService.save(route));
    }

    @PutMapping("/admin/route/{id}")
    public ResponseEntity<Ruta> updateRoute(@PathVariable String id, @RequestBody Ruta routeUpdated) {
        if (!routeService.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        routeUpdated.setId(id);
        return ResponseEntity.ok(routeService.save(routeUpdated));
    }

    @DeleteMapping("/admin/route/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable String id) {
        if (!routeService.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        routeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
