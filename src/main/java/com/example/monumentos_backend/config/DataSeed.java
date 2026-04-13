/*
package com.example.monumentos_backend.config;

import com.example.monumentos_backend.model.Monument;
import com.example.monumentos_backend.repository.MonumentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataSeed implements CommandLineRunner {

    private final MonumentRepository monumentRepository;

    public DataSeed(MonumentRepository monumentRepository) {
        this.monumentRepository = monumentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (monumentRepository.count() == 0) {
            Monument m = new Monument();
            m.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440005"));
            m.setName("Antigua Cárcel y Cabildo");
            m.setActivate(true);
            m.setLat(37.7229506);
            m.setLon(-3.9683925);
            m.setAccessibility(true);
            m.setNLikes(70);
            m.setMapsUrl("https://www.google.com/maps/place/Antigua+C%C3%A1rcel+y+Cabildo/@37.7229506,-3.9683925,17z/data=!3m1!4b1!4m6!3m5!1s0xd6dc5ec47ffadbf:0x2b37e5b4c3713eac!8m2!3d37.7229464!4d-3.9658176!16s%2Fg%2F1223q83k?entry=ttu&g_ep=EgoyMDI2MDMxOC4xIKXMDSoASAFQAw%3D%3D");

            monumentRepository.save(m);

            System.out.println("Conexión creada exitosamente");
        } else {
            System.out.println("La base de datos ya contiene datos");
        }
    }
}
*/
