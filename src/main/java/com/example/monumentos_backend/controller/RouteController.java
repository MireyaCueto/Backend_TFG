package com.example.monumentos_backend.controller;

import com.example.monumentos_backend.model.Monument;
import com.example.monumentos_backend.model.Ruta;
import com.example.monumentos_backend.model.Score;
import com.example.monumentos_backend.service.RouteService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    // EndPoints Publicos

    @GetMapping("/public/route")
    public List<Ruta> getAllRoutes(
            @RequestParam(required = false) String name,
            @RequestParam(name = "isActive", required = false) Boolean activate,
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String orderBy
    ) {
        System.out.println("tag = " + tag);
        return routeService.findByFilters(name, activate, tag, sortBy, orderBy);
    }

    @GetMapping("/public/route/{id}")
    public ResponseEntity<Ruta> getRouteById(@PathVariable String id) {
        Optional<Ruta> route = routeService.getById(id);
        return route.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/public/route/{routeId}/score")
    public ResponseEntity<Score> addScoreToRoute(@PathVariable String routeId, @RequestBody Score score) {
        if (!routeService.existsById(routeId)) {
            return ResponseEntity.notFound().build();
        }

        score.setRouteId(routeId);
        return ResponseEntity.ok(routeService.saveScore(score));
    }

    // Endpoints Privados

    @PostMapping("/admin/route")
    public ResponseEntity<Optional<Ruta>> saveRoute(@RequestBody Ruta route) {
        return ResponseEntity.ok(routeService.save(route));
    }

    @PatchMapping("/admin/route/{id}/activate")
    public ResponseEntity<Ruta> activaRuta(@PathVariable String id) {   
        Ruta rutaActualizada = routeService.activateRoute(id);
        if (rutaActualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rutaActualizada);
    }

    @PutMapping("/admin/route/{id}")
    public ResponseEntity<Optional<Ruta>> updateRoute(@PathVariable String id, @RequestBody Ruta routeUpdated) {
        if (!routeService.existsById(id)) {
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
