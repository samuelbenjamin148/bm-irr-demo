package com.example.demo.sensor;

import com.example.demo.plot.Plot;
import jakarta.persistence.*;

@Entity
@Table
public class Sensor {
    public Sensor() {

    }

    public enum Status {
        ACTIVE,
        INACTIVE
    }

    public Sensor(String name, String ipAddress, Integer maxRetries, Status status, Plot plot) {
        this.name = name;
        this.ipAddress = ipAddress;
        this.maxRetries = maxRetries;
        this.status = status;
        this.plot = plot;
    }

    public Sensor(String name, String ipAddress, Integer maxRetries, Status status) {
        this.name = name;
        this.ipAddress = ipAddress;
        this.maxRetries = maxRetries;
        this.status = status;
    }

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "id")
    private Long id;
    private String name;
    @Column(nullable = false)
    private String ipAddress;
    private Integer maxRetries = 1; //default to 1
    private Status status;
    @ManyToOne()
    @JoinColumn(name="plot_id", referencedColumnName = "id", nullable = false)
    private Plot plot;

    public Plot getPlot() {
        return plot;
    }

    public void registerPlot(Plot plot) {
        this.plot = plot;
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

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(Integer maxRetries) {
        this.maxRetries = maxRetries;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPlot(Plot plot) {
        this.plot = plot;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", maxRetries=" + maxRetries +
                ", status=" + status +
                ", plot=" + plot +
                '}';
    }
}
