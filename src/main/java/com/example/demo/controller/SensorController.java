package com.example.demo.controller;

import com.example.demo.sensor.Sensor;
import com.example.demo.sensor.SensorForm;
import com.example.demo.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/sensor")
public class SensorController {

    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
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

    @GetMapping(path = "/irrigate/{plotId}/{waterAmount}/{duration}")
    public void irrigate(
            @PathVariable Long plotId,
            @PathVariable Integer waterAmount,
            @PathVariable Integer duration) {
        sensorService.irrigate(plotId, waterAmount, duration);
    }

    @DeleteMapping(path= "{plotID}")
    public void deleteSensor(@PathVariable("plotID") Long id) {
        sensorService.deleteSensor(id);
    }

}
