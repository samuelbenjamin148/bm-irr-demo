package com.example.demo.controller;

import com.example.demo.plot.Plot;
import com.example.demo.sensor.Sensor;
import com.example.demo.service.PlotService;
import com.example.demo.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/plot")
public class PlotController {

    private final PlotService plotService;
    private final SensorService sensorService;

    @Autowired
    public PlotController(
            PlotService plotService,
            SensorService sensorService) {
        this.plotService = plotService;
        this.sensorService = sensorService;
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

    @PutMapping(path = "{plotID}/sensor/{sensorID}")
    Sensor registerSensorToPlot(
            @PathVariable Long plotID,
            @PathVariable Long sensorID) {
        return sensorService.registerSensor(plotID, sensorID);
    }



}

