package com.example.monumentos_backend.repository;

import com.example.monumentos_backend.model.Stat;
import com.example.monumentos_backend.dto.StatAggregation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StatRepository extends JpaRepository<Stat, Integer> {

    Optional<Stat> findByNameServiceAndRegisDate(String nameService, LocalDate regisDate);

    // Agrupación DIARIA
    @Query(value = "SELECT TO_CHAR(regis_date, 'YYYY-MM-DD') AS period, SUM(n_downloads) AS totalDownloads " +
            "FROM stats WHERE name_service = :nameService GROUP BY period ORDER BY period", nativeQuery = true)
    List<StatAggregation> getDailyDownloads(@Param("nameService") String nameService);

    // Agrupación MENSUAL
    @Query(value = "SELECT TO_CHAR(regis_date, 'YYYY-MM') AS period, SUM(n_downloads) AS totalDownloads " +
            "FROM stats WHERE name_service = :nameService GROUP BY period ORDER BY period", nativeQuery = true)
    List<StatAggregation> getMonthlyDownloads(@Param("nameService") String nameService);

    // Agrupación ANUAL
    @Query(value = "SELECT TO_CHAR(regis_date, 'YYYY') AS period, SUM(n_downloads) AS totalDownloads " +
            "FROM stats WHERE name_service = :nameService GROUP BY period ORDER BY period", nativeQuery = true)
    List<StatAggregation> getYearlyDownloads(@Param("nameService") String nameService);

    interface StatSummaryProjection {
        String getName();

        Long getTotalDownloads();
    }

    @Query(value = "SELECT name_service AS name, COALESCE(SUM(n_downloads), 0) AS totalDownloads " +
            "FROM stats WHERE TO_CHAR(regis_date, 'YYYY-MM-DD') LIKE :period || '%' GROUP BY name_service", nativeQuery = true)
    List<StatSummaryProjection> getDownloadsGroupedByService(@Param("period") String period);
}