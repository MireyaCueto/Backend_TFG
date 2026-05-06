package com.example.monumentos_backend.service;

import com.example.monumentos_backend.model.Stat;
import com.example.monumentos_backend.repository.AppReviewRepository;
import com.example.monumentos_backend.repository.StatRepository;
import com.example.monumentos_backend.dto.GeneralStatResponse;
import com.example.monumentos_backend.dto.StatAggregation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<StatAggregation> getDailyStats(String serviceName) {
        return statRepository.getDailyDownloads(serviceName);
    }

    public List<StatAggregation> getMonthlyStats(String serviceName) {
        return statRepository.getMonthlyDownloads(serviceName);
    }

    public List<StatAggregation> getYearlyStats(String serviceName) {
        return statRepository.getYearlyDownloads(serviceName);
    }
}