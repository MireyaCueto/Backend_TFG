package com.example.monumentos_backend.controller;

import com.example.monumentos_backend.model.Monument;
import com.example.monumentos_backend.service.MonumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class MonumentController {

    private final MonumentService monumentService;

    public MonumentController(MonumentService monumentService) {
        this.monumentService = monumentService;
    }

    // Endpoints Públicos

    @GetMapping("/public/monuments")
    public List<Monument> getAllMonuments(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) Boolean accessibility,
            @RequestParam(name = "isActive", required = false) Boolean activate,
            @RequestParam(required = false) String orderBy
    ) {

        System.out.println("name = " + name);
        System.out.println("tag = " + tag);
        System.out.println("accessibility = " + accessibility);
        System.out.println("activate = " + activate);

        return monumentService.findByFilters(name, tag, accessibility, activate, orderBy);
    }

    @GetMapping("/public/monuments/{id}")
    public ResponseEntity<Monument> getMonumentById(@PathVariable String id) {
        Optional<Monument> monument = monumentService.getById(id);
        return monument.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoints privados

    @PostMapping("/admin/monuments")
    public Monument saveMonument(@RequestBody Monument monument) {
        return monumentService.save(monument);
    }

    @PutMapping("/admin/monuments/{id}")
    public ResponseEntity<Monument> updateMonument(@PathVariable String id, @RequestBody Monument monumentUpdated) {
        if (!monumentService.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        monumentUpdated.setId(id);
        return ResponseEntity.ok(monumentService.save(monumentUpdated));
    }

    @DeleteMapping("/admin/monuments/{id}")
    public ResponseEntity<Void> deleteMonument(@PathVariable String id) {
        if (!monumentService.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        monumentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
