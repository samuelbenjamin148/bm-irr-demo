package com.example.demo.sensor;

public class SensorForm {
    private String name;
    private String ipAddress;
    private Integer maxRetries;
    private Sensor.Status status;
    private Long plotID;

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

    public Sensor.Status getStatus() {
        return status;
    }

    public void setStatus(Sensor.Status status) {
        this.status = status;
    }

    public Long getPlotID() {
        return plotID;
    }

    public void setPlotID(Long plotID) {
        this.plotID = plotID;
    }


}
