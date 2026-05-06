package com.example.monumentos_backend.controller;

import com.example.monumentos_backend.dto.StatAggregation;
import com.example.monumentos_backend.service.StatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/stats")
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
}