package com.example.demo.plot;

import com.example.demo.repository.PlotRepository;
import com.example.demo.repository.ScheduleRepository;
import com.example.demo.repository.SensorRepository;
import com.example.demo.scheduler.Schedule;
import com.example.demo.sensor.Sensor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

// Configuration class for plots, tasks , and sensors

@Configuration
public class PlotConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            SensorRepository sensorRepository,
            PlotRepository plotRepository,
            ScheduleRepository scheduleRepository) {
        return args -> {
                Plot plot1 = new Plot(
                        "plot1",
                        20,
                        20,
                        20,
                        100
                );
            Plot plot2 = new Plot(
                    "plot2",
                    30,
                    40,
                    50,
                    100
            );

            Sensor sensor1 = new Sensor(
                    "sensor1",
                    "10.10.10.10",
                    1,
                    Sensor.Status.ACTIVE,plot1) ;

            Sensor sensor2 = new Sensor(
                    "sensor2",
                    "20.20.20.20",
                    1,
                    Sensor.Status.ACTIVE, plot2);

            Sensor sensor3 = new Sensor(
                    "sensor3",
                    "30.30.30.30",
                    1,
                    Sensor.Status.INACTIVE, plot1);

            Schedule schedule1 = new Schedule(1000L,
                    "*/5 * * * * *",
                     1,
                    1,
                     plot1
            );
            Schedule schedule2 = new Schedule(1002L,
                    "*/10 * * * * *",
                    1,
                    1,
                    plot2
            );

            plotRepository.saveAll(
                    List.of(plot1, plot2));
            sensorRepository.saveAll(
                    List.of(sensor1, sensor2, sensor3));
            scheduleRepository.saveAll(
                    List.of(schedule1, schedule2));
        };
    }
}
