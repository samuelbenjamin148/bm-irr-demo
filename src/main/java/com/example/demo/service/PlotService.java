package com.example.demo.service;

import com.example.demo.plot.Plot;
import com.example.demo.sensor.Sensor;
import com.example.demo.sensor.SensorForm;
import com.example.demo.repository.PlotRepository;
import com.example.demo.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PlotService {

    private final PlotRepository plotRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public PlotService(PlotRepository plotRepository, SensorRepository sensorRepository) {
        this.plotRepository = plotRepository;
        this.sensorRepository = sensorRepository;
    }

    public List<Plot> getPlots() {
        return plotRepository.findAll();
    }

    public void addNewPlot(Plot plot) {
        if (plotRepository.findPlotByName(plot.getName()).isPresent()) {
            throw new IllegalStateException("Plot name already exists");
        }
        plotRepository.save(plot);
    }

    private void validatePlotId( Long plotID) {
        if (!plotRepository.existsById(plotID)) {
            throw new IllegalStateException(
                    "plot with Id" + plotID + " does not exist");
        }
    }
    public void deletePlot(Long  plotID) {
        validatePlotId(plotID);
        plotRepository.deleteById(plotID);
    }

    @Transactional
    public void updatePlot(Long plotID, String name, Integer width, Integer length) {
        Plot plot = plotRepository.findById(plotID)
                        .orElseThrow(()-> new IllegalStateException(
                                "plot with id " + plotID + "does not exist"));

        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(plot.getName(), name)) {
            Optional<Plot> plotOptional = plotRepository.findPlotByName(name);
            if (plotOptional.isPresent()) {
                throw new IllegalStateException("name already exists");
            }
        }
        plot.setName(name);

        if (width != null &&
                width > 0 &&
                !Objects.equals(plot.getWidth(), width)) {
            plot.setWidth(width);
        }


        if (length != null &&
                length > 0 &&
                !Objects.equals(plot.getLength(), length)) {
            plot.setWidth(length);
        }

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
}
