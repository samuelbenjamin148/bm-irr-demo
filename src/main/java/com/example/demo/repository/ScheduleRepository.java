package com.example.demo.repository;

import com.example.demo.scheduler.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Schedule WHERE Id = :Id")
    Integer removeSchdulebyID(Long Id);
}
