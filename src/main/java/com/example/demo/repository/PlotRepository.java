package com.example.demo.repository;

import com.example.demo.plot.Plot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlotRepository
        extends JpaRepository<Plot, Long> {

    @Query("SELECT s FROM Plot s WHERE s.name = ?1")
    Optional<Plot> findPlotByName(String name);

    @Query("SELECT s FROM Plot s WHERE s.id = ?1")
    Optional<Plot> findPlotById(Long id);

}
