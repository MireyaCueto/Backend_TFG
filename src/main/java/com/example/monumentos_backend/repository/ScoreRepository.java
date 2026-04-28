package com.example.monumentos_backend.repository;

import com.example.monumentos_backend.model.Score;
import com.example.monumentos_backend.model.ScoreId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// Fíjate que el segundo parámetro ahora es ScoreId
@Repository
public interface ScoreRepository extends JpaRepository<Score, ScoreId> {

    @Query("SELECT AVG(s.score) FROM Score s WHERE s.routeId = ?1")
    Double getAverageScoreByRouteId(String routeId);
}