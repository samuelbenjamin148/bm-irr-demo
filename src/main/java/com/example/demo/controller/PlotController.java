package com.example.demo.controller;

import com.example.demo.plot.Plot;
import com.example.demo.service.PlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/plot")
public class PlotController {

    private final PlotService plotService;

    @Autowired
    public PlotController(PlotService plotService) {
        this.plotService = plotService;
    }

    @GetMapping
    public List<Plot> getPlots() {
        return  plotService.getPlots();
    }

    @PostMapping
    public void registerNewPlot(@RequestBody Plot plot) {
        plotService.addNewPlot(plot);
    }

    @DeleteMapping(path= "{plotID}")
    public void deletePlot(@PathVariable("plotID") Long id) {
        plotService.deletePlot(id);
    }

    @PutMapping(path = "{plotID}")
    public void updatePlot(
            @PathVariable("plotID") Long plotID,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer width,
            @RequestParam(required = false) Integer length) {
        plotService.updatePlot(plotID, name, width, length);
    }

}

