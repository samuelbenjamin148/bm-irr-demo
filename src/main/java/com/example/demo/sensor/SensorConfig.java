package com.example.demo.sensor;

import com.example.demo.repository.PlotRepository;
import com.example.demo.repository.SensorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SensorConfig {
   /* @Bean
    CommandLineRunner SensorcommandLineRunner(SensorRepository repository) {
       return args -> {
            Sensor sensor1 = new Sensor(
                    "sensor1",
                    "10.10.10.10",
                    1,
                    Sensor.Status.ACTIVE,1) ;

            Sensor sensor2 = new Sensor(
                    "sensor2",
                    "20.20.20.20",
                    1,
                    Sensor.Status.ACTIVE);

            Sensor sensor3 = new Sensor(
                    "sensor3",
                    "30.30.30.30",
                    1,
                    Sensor.Status.INACTIVE);

            repository.saveAll(
                    List.of(sensor1, sensor2, sensor3));
        };
    }

    */
}
