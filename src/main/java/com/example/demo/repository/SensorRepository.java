package com.example.demo.repository;

import com.example.demo.sensor.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

    @Query("SELECT s FROM Sensor s WHERE s.ipAddress = ?1")
    Optional<Sensor> findSensorByIP(String ipAddress);

    @Query("SELECT s FROM Sensor as s where s.plot.id = :plotId")
    List<Sensor> findSensorByPlotId(Long plotId);
}
