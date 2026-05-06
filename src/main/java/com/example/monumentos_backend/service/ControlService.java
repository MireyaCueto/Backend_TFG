package com.example.monumentos_backend.service;

import com.example.monumentos_backend.model.Control;
import com.example.monumentos_backend.repository.ControlRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ControlService {

    private final ControlRepository controlRepository;

    public ControlService(ControlRepository controlRepository) {
        this.controlRepository = controlRepository;
    }

    public List<Control> findAll() {
        return controlRepository.findAll();
    }

    // Método principal para comprobar si una sección está activa
    public boolean isFeatureActive(String name) {
        return controlRepository.findByName(name)
                .map(Control::getActive)
                .orElse(false); // Si no existe en la base de datos, por seguridad decimos que está apagado
    }

    // Guardar o actualizar un control
    public Control save(Control control) {
        if (control.getId() == null) {
            control.setCreatedAt(LocalDateTime.now());
        }
        control.setLastModified(LocalDateTime.now());
        return controlRepository.save(control);
    }
}