package com.example.monumentos_backend.controller;

import com.example.monumentos_backend.model.Control;
import com.example.monumentos_backend.service.ControlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ControlController {

    private final ControlService controlService;

    public ControlController(ControlService controlService) {
        this.controlService = controlService;
    }

    // Devuelve true o false dependiendo de si la sección está encendida
    @GetMapping("/public/control/{name}/status")
    public ResponseEntity<Boolean> checkFeatureStatus(@PathVariable String name) {
        return ResponseEntity.ok(controlService.isFeatureActive(name));
    }

    // Devuelve todas las configuraciones de golpe para que la app las cargue al
    // iniciar
    @GetMapping("/public/control")
    public ResponseEntity<List<Control>> getAllControls() {
        return ResponseEntity.ok(controlService.findAll());
    }

    @PutMapping("/admin/control/{name}")
    public ResponseEntity<Control> createOrUpdateControl(
            @PathVariable String name,
            @RequestBody Control control) {

        control.setName(name.toUpperCase());

        controlService.findAll().stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst()
                .ifPresent(existing -> control.setId(existing.getId()));

        return ResponseEntity.ok(controlService.save(control));
    }
}