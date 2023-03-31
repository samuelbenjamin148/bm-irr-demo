package com.example.demo.plot;

import com.example.demo.repository.PlotRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PlotConfig {

    @Bean
    CommandLineRunner commandLineRunner(PlotRepository repository) {
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

            repository.saveAll(
                    List.of(plot1, plot2));
        };
    }
}
