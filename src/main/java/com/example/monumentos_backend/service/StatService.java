package com.example.monumentos_backend.service;

import com.example.monumentos_backend.model.Stat;
import com.example.monumentos_backend.model.StatsIA;
import com.example.monumentos_backend.model.AppReview;
import com.example.monumentos_backend.repository.AppReviewRepository;
import com.example.monumentos_backend.repository.StatRepository;
import com.example.monumentos_backend.repository.StatsIARepository;
import com.example.monumentos_backend.dto.GeneralStatResponse;
import com.example.monumentos_backend.dto.StatAggregation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatService {

    private final StatRepository statRepository;
    private final AppReviewRepository appReviewRepository;

    public StatService(StatRepository statRepository, AppReviewRepository appReviewRepository) {
        this.statRepository = statRepository;
        this.appReviewRepository = appReviewRepository;
    }

    // Nuevo método para las estadísticas generales
    public Object getGeneralStats(String period, String serviceName) {
        // 1. Traemos descargas y puntuaciones
        List<StatRepository.StatSummaryProjection> downloads = statRepository.getDownloadsGroupedByService(period);
        List<AppReviewRepository.ReviewSummaryProjection> scores = appReviewRepository.getAllAverageScores();

        // 2. Mapeamos las puntuaciones para buscar rápido
        Map<String, Double> scoreMap = new HashMap<>();
        for (AppReviewRepository.ReviewSummaryProjection score : scores) {
            scoreMap.put(score.getName(), score.getAverageScore());
        }

        List<GeneralStatResponse> resultList = new ArrayList<>();
        long generalDownloads = 0;

        // 3. Cruzamos los datos
        for (StatRepository.StatSummaryProjection d : downloads) {
            String name = d.getName();
            Long total = d.getTotalDownloads();
            Double avg = scoreMap.getOrDefault(name, 0.0);

            avg = Math.round(avg * 100.0) / 100.0; // Redondear a 2 decimales
            generalDownloads += total;

            if (serviceName == null || serviceName.equalsIgnoreCase(name)) {
                resultList.add(new GeneralStatResponse(name, period, total, avg));
            }
        }

        // 4. Si el usuario pidió uno específico, devolvemos solo ese objeto (no una
        // lista)
        if (serviceName != null) {
            return resultList.isEmpty() ? null : resultList.get(0);
        }

        // 5. Si no, calculamos el total "general" y lo añadimos a la lista
        Double globalAvg = appReviewRepository.getGlobalAverageScore();
        globalAvg = globalAvg != null ? Math.round(globalAvg * 100.0) / 100.0 : 0.0;

        resultList.add(new GeneralStatResponse("general", period, generalDownloads, globalAvg));

        return resultList;
    }

    public Stat registerDownload(String serviceName) {
        LocalDate today = LocalDate.now();

        Stat stat = statRepository.findByNameServiceAndRegisDate(serviceName, today)
                .orElseGet(() -> {
                    Stat newStat = new Stat();
                    newStat.setNameService(serviceName);
                    newStat.setRegisDate(today);
                    newStat.setNDownloads(0);
                    return newStat;
                });

        stat.setNDownloads(stat.getNDownloads() + 1);
        return statRepository.save(stat);
    }

    public AppReview saveReview(AppReview review) {
        if (review.getIdDevice() == null || review.getIdDevice().isBlank()
                || review.getNameService() == null || review.getNameService().isBlank()
                || review.getScore() == null) {
            throw new IllegalArgumentException("id_device, name_service y score son obligatorios");
        }

        return appReviewRepository
                .findByIdDeviceAndNameService(review.getIdDevice(), review.getNameService())
                .map(existing -> {
                    existing.setScore(review.getScore());
                    return appReviewRepository.save(existing);
                })
                .orElseGet(() -> {
                    review.setCreatedAt(LocalDateTime.now());
                    return appReviewRepository.save(review);
                });
    }

    public List<StatAggregation> getDailyStats(String serviceName) {
        return statRepository.getDailyDownloads(serviceName);
    }

    public List<StatAggregation> getMonthlyStats(String serviceName) {
        return statRepository.getMonthlyDownloads(serviceName);
    }

    public List<StatAggregation> getYearlyStats(String serviceName) {
        return statRepository.getYearlyDownloads(serviceName);
    }

    // Servicio para peticiiones a la IA

    @Autowired
    private StatsIARepository statsRepository;

    // Incrementa el contador de éxitos (+1)
    public void registrarPeticionesTotales() {
        statsRepository.incrementCount("peticiones_completas");
    }

    // Incrementa el contador de fallos (+1)
    public void registrarPeticionFallida() {
        statsRepository.incrementCount("peticiones_fallidas");
    }

    // Devuelve la lista completa (los dos registros)
    public List<StatsIA> obtenerTodas() {
        return statsRepository.findAll();
    }

    // Busca un contador específico por su nombre
    public StatsIA obtenerPorNombre(String nombre) {
        return statsRepository.findByNameCount(nombre)
                .orElseThrow(() -> new RuntimeException("Estadística no encontrada: " + nombre));
    }
}
