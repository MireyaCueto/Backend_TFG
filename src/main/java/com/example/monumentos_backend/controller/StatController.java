package com.example.monumentos_backend.controller;

import com.example.monumentos_backend.dto.StatAggregation;
import com.example.monumentos_backend.model.StatsIA;
import com.example.monumentos_backend.service.StatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/stats")
@CrossOrigin(origins = "*")
public class StatController {

    private final StatService statService;

    public StatController(StatService statService) {
        this.statService = statService;
    }

    @GetMapping("/{serviceName}/daily")
    public ResponseEntity<List<StatAggregation>> getDailyStats(@PathVariable String serviceName) {
        return ResponseEntity.ok(statService.getDailyStats(serviceName));
    }

    @GetMapping("/{serviceName}/monthly")
    public ResponseEntity<List<StatAggregation>> getMonthlyStats(@PathVariable String serviceName) {
        return ResponseEntity.ok(statService.getMonthlyStats(serviceName));
    }

    @GetMapping("/{serviceName}/yearly")
    public ResponseEntity<List<StatAggregation>> getYearlyStats(@PathVariable String serviceName) {
        return ResponseEntity.ok(statService.getYearlyStats(serviceName));
    }

    @GetMapping("/summary")
    public ResponseEntity<Object> getSummary(
            @RequestParam(name = "period") String period,
            @RequestParam(name = "service", required = false) String serviceName) {

        Object response = statService.getGeneralStats(period, serviceName);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    // Endpoints para estadísticas de la IA

    @GetMapping("/all-ia")
    public List<StatsIA> listarEstadisticas() {
        return statService.obtenerTodas();
    }

    // Endpoint para sumar un acierto
    @PostMapping("/new-request")
    public ResponseEntity<String> newRequest() {
        statService.registrarPeticionesTotales();
        return ResponseEntity.ok("Conteo de peticiones totales incrementado.");
    }

    // Endpoint para sumar un fallo
    @PostMapping("/fail-request")
    public ResponseEntity<String> failRequest() {
        statService.registrarPeticionFallida();
        return ResponseEntity.ok("Conteo de peticiones fallidas incrementado.");
    }

}