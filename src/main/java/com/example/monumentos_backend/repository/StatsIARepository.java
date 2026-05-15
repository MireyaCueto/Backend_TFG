package com.example.monumentos_backend.repository;

import com.example.monumentos_backend.model.StatsIA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface StatsIARepository extends JpaRepository<StatsIA, Long> {

    Optional<StatsIA> findByNameCount(String nameCount);

    @Transactional
    @Modifying
    @Query("UPDATE StatsIA s SET s.count = s.count + 1, s.lastModified = CURRENT_TIMESTAMP WHERE s.nameCount = :name")
    void incrementCount(@Param("name") String name);
}
