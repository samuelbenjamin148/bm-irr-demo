package com.example.demo.plot;

import com.example.demo.scheduler.Schedule;
import com.example.demo.sensor.Sensor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Plot {

    @Id
    @SequenceGenerator(
            name = "plot_sequence",
            sequenceName = "plot_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "plot_sequence"
    )

    @Column(name = "id")
    private Long id;
    @Column(name="plot_name")
    private String name;
    private Integer width;
    private Integer length;
    private Integer xCoordinate;
    private Integer yCoordinate;
    private String  lastIrrigated = null;

    public Set<Sensor> getRegisteredSensors() {
        return registeredSensors;
    }

    @JsonIgnore
    @OneToMany(
            mappedBy="plot",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Sensor> registeredSensors = new HashSet<>();

    @JsonIgnore
    @OneToMany(
            mappedBy="plot",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    private Set<Schedule> registeredSchedules = new HashSet<>();

    public Plot() {
    }

    public Plot(Long id,
                String name,
                Integer width,
                Integer length,
                Integer xCoordinate,
                Integer yCoordinate) {

        this.id = id;
        this.name = name;
        this.width = width;
        this.length = length;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public Plot(String name,
                Integer width,
                Integer length,
                Integer xCoordinate,
                Integer yCoordinate) {

        this.name = name;
        this.width = width;
        this.length = length;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(Integer xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Integer getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(Integer yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public String getLastIrrigated() {
        return lastIrrigated;
    }

    public void setLastIrrigated(String lastIrrigated) {
        this.lastIrrigated = lastIrrigated;

    }

    @Override
    public String toString() {
        return "Plot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", width=" + width +
                ", length=" + length +
                ", xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                ", lastIrrigated='" + lastIrrigated + '\'' +
                ", registeredSensors=" + registeredSensors +
                ", registeredSchedules=" + registeredSchedules +
                '}';
    }
}
