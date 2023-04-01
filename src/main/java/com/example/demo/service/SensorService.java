package com.example.demo.service;

import com.example.demo.plot.Plot;
import com.example.demo.repository.PlotRepository;
import com.example.demo.repository.SensorRepository;
import com.example.demo.sensor.Sensor;
import com.example.demo.sensor.SensorForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService {

    private final PlotRepository plotRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(PlotRepository plotRepository, SensorRepository sensorRepository) {
        this.plotRepository = plotRepository;
        this.sensorRepository = sensorRepository;
    }
    @Transactional
    public Sensor registerSensor(Long plotID, Long sensorID) {
        Optional<Sensor> sensorOptional = sensorRepository.findById(sensorID);
        if (!sensorOptional.isPresent()) {
            throw new IllegalStateException("" +
                    "sensor with ID = " + sensorID + " does not exist");
        }

        Optional<Plot> plotOptional = plotRepository.findById(plotID);
        if (!plotOptional.isPresent()) {
            throw new IllegalStateException("" +
                    "Plot with ID = " + plotID + " does not exist");
        }
        sensorOptional.get().registerPlot(plotOptional.get());
        return sensorOptional.get();

    }

    public List<Sensor> getSensors() {
        return sensorRepository.findAll();
    }

    public void addNewSensor(SensorForm sensor) {
        if (sensorRepository.findSensorByIP(sensor.getIpAddress()).isPresent()) {
            throw new IllegalStateException(
                    "Sensor with IP "
                            + sensor.getIpAddress()
                            + " address already exists");
        }

        Optional<Plot> optionalPlot = plotRepository.findById(sensor.getPlotID());
        if (!optionalPlot.isPresent()) {
            throw new IllegalStateException("Plot ID is not a valid plot");
        }
        Sensor mSensor = new Sensor();
        mSensor.setName(sensor.getName());
        mSensor.setIpAddress(sensor.getIpAddress());
        mSensor.setMaxRetries(sensor.getMaxRetries());
        mSensor.setStatus(sensor.getStatus());
        mSensor.setPlot(optionalPlot.get());
        sensorRepository.save(mSensor);
    }

    @Transactional
    public void irrigate(Long plotId, Integer waterAmount, Integer duration) {
        System.out.print("Irrigating Plot Id = " + plotId);

        List<Sensor> sensorList = sensorRepository.findSensorByPlotId(plotId);
        Plot plot = plotRepository.findPlotById(plotId).get();

        boolean sensorFailure = false;
        for (int i = 0 ; i < sensorList.size(); i++) {
            Sensor s = sensorList.get(i);
            if (s.getStatus() == Sensor.Status.INACTIVE) {
                sensorFailure = true;
                if (s.getMaxRetries() < 0) {
                    System.out.print("Sensor with ID " + s.getId() +
                            " has exceeded max retries");
                } else {
                    s.setMaxRetries(s.getMaxRetries() - 1);
                }
            }

        }
        if (!sensorFailure) {
            plot.setLastIrrigated(java.time.LocalTime.now().toString());
            System.out.print("Plot with ID " + plot.getId() +
                    " has been irrigated for duration =" +
                    duration + "waterAmount =  " + waterAmount);
        }

    }

    public void deleteSensor(Long id) {
        if (!sensorRepository.existsById(id)) {
            throw new IllegalStateException(
                    "Sensor with Id" + id + " does not exist");
        }
        sensorRepository.deleteById(id);
    }
}
