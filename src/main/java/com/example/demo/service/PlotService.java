package com.example.demo.service;

import com.example.demo.plot.Plot;
import com.example.demo.repository.PlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PlotService {

    private final PlotRepository plotRepository;

    @Autowired
    public PlotService(PlotRepository plotRepository) {
        this.plotRepository = plotRepository;
    }

    public List<Plot> getPlots() {
        return plotRepository.findAll();
    }

    public void addNewPlot(Plot plot) {

        if (plotRepository.findPlotByName(plot.getName()).isPresent()) {
            throw new IllegalStateException("Plot name already exists");
        }
        //TODO :: Verify the plots sizing is not overlaping with others
        plotRepository.save(plot);
    }

    private void validatePlotId( Long plotID) {
        //This is a private function to make code cleaner in case this is going
        // to be repeated more than 1 time
        if (!plotRepository.existsById(plotID)) {
            throw new IllegalStateException(
                    "plot with Id" + plotID + " does not exist");
        }
    }
    public void deletePlot(Long plotID) {
        validatePlotId(plotID);
        plotRepository.deleteById(plotID);
    }


    // Update the plot details
    @Transactional
    public void updatePlot(Long plotID, String name, Integer width, Integer length) {
        // TODO: handle exceptions in a better way
        Plot plot = plotRepository.findById(plotID)
                        .orElseThrow(()-> new IllegalStateException(
                                "plot with id " + plotID + "does not exist"));

        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(plot.getName(), name)) {
            Optional<Plot> plotOptional = plotRepository.findPlotByName(name);
            if (plotOptional.isPresent()) {
                // TODO: handle exceptions in a better way
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

}
