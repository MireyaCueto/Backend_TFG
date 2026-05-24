package com.example.monumentos_backend.controller;

import com.example.monumentos_backend.model.AppReview;
import com.example.monumentos_backend.model.Stat;
import com.example.monumentos_backend.service.StatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/public/stats")
public class PublicStatController {

    private final StatService statService;

    public PublicStatController(StatService statService) {
        this.statService = statService;
    }

    @PostMapping("/download")
    public ResponseEntity<Stat> registerDownload(@RequestParam(name = "service") String serviceName) {
        return ResponseEntity.ok(statService.registerDownload(serviceName));
    }

    @PostMapping("/review")
    public ResponseEntity<AppReview> saveReview(@RequestBody AppReview review) {
        return ResponseEntity.ok(statService.saveReview(review));
    }

    @PostMapping("/ia/new-request")
    public ResponseEntity<String> registerIaSuccess() {
        statService.registrarPeticionesTotales();
        return ResponseEntity.ok("Conteo de peticiones totales incrementado.");
    }

    @PostMapping("/ia/fail-request")
    public ResponseEntity<String> registerIaFailure() {
        statService.registrarPeticionFallida();
        return ResponseEntity.ok("Conteo de peticiones fallidas incrementado.");
    }
}
