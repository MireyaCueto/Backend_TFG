package com.example.monumentos_backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // Mejor importar todo para usar PostMapping, PutMapping, etc.

import com.example.monumentos_backend.model.Noticia;
import com.example.monumentos_backend.service.NoticiaService;

@RestController
@RequestMapping("/api/v1")
public class NewController {

    private final NoticiaService noticiaService;

    public NewController(NoticiaService noticiaService) {
        this.noticiaService = noticiaService;
    }

    // --- Endpoints Públicos ---

    @GetMapping("/public/news")
    public List<Noticia> getAllNews() {
        return noticiaService.findAll();
    }

    @GetMapping("/public/news/{id}")
    public ResponseEntity<Noticia> getNewById(@PathVariable String id) {
        Optional<Noticia> noticia = noticiaService.getById(id);
        return noticia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // --- Endpoints Privados (Admin) ---

    @PostMapping("/admin/news")
    public ResponseEntity<Noticia> saveNews(@RequestBody Noticia noticia) {
        return ResponseEntity.ok(noticiaService.save(noticia));
    }

    @PutMapping("/admin/news/{id}")
    public ResponseEntity<Noticia> updateNews(@PathVariable String id, @RequestBody Noticia noticiaUpdated) {
        System.out.println("hola");
        if (!noticiaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        noticiaUpdated.setId(id);
        return ResponseEntity.ok(noticiaService.save(noticiaUpdated));
    }

    @DeleteMapping("/admin/news/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable String id) {
        if (!noticiaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        noticiaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/admin/news/{id}/publish")
    public ResponseEntity<Noticia> publishNews(@PathVariable String id) {
        System.out.println("hola");
        Optional<Noticia> noticiaPublicada = noticiaService.publishNoticia(id);

        return noticiaPublicada.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}