package com.example.monumentos_backend.repository;

import com.example.monumentos_backend.model.AppReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppReviewRepository extends JpaRepository<AppReview, Integer> {
    // Buscar si un dispositivo ya ha votado en un servicio
    Optional<AppReview> findByIdDeviceAndNameService(String idDevice, String nameService);

    // Calcular la nota media directamente en la base de datos
    @Query("SELECT COALESCE(AVG(r.score), 0.0) FROM AppReview r WHERE r.nameService = :nameService")
    Double getAverageScoreByNameService(String nameService);

    interface ReviewSummaryProjection {
        String getName();

        Double getAverageScore();
    }

    @Query(value = "SELECT name_service AS name, COALESCE(AVG(score), 0.0) AS averageScore FROM app_reviews GROUP BY name_service", nativeQuery = true)
    List<ReviewSummaryProjection> getAllAverageScores();

    @Query(value = "SELECT COALESCE(AVG(score), 0.0) FROM app_reviews", nativeQuery = true)
    Double getGlobalAverageScore();
}