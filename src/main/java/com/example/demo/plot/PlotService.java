package com.example.demo.plot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlotService {

    @Autowired
    public PlotService(PlotRepository plotRepository) {
        this.plotRepository = plotRepository;
    }

    private final PlotRepository plotRepository;

    public List<Plot> getPlots() {
        return plotRepository.findAll();
    }
}
