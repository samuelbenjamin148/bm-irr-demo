package com.example.demo.controller;

import com.example.demo.sensor.Sensor;
import com.example.demo.sensor.SensorForm;
import com.example.demo.service.PlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/sensor")
public class SensorController {

    private final PlotService sensorService;

    @Autowired
    public SensorController(PlotService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping
    public List<Sensor> getSensors() {
        return sensorService.getSensors();
    }

    @PostMapping
    public void registerNewSensor(@RequestBody SensorForm sensor) {
        sensorService.addNewSensor(sensor);
    }

}
