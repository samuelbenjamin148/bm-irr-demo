package com.example.demo.scheduler;

import com.example.demo.plot.Plot;
import jakarta.persistence.*;


@Entity
@Table
public class Schedule {


    @Id
    @Column(name = "Id")
    private Long taskId;

    private String cronExpression;
    private Integer waterAmount;
    private Integer duration; // in seconds

    @ManyToOne()
    @JoinColumn(name="plot_id", referencedColumnName = "id", nullable = false)
    private Plot plot;


    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Integer getWaterAmount() {
        return waterAmount;
    }

    public void setWaterAmount(Integer waterAmount) {
        this.waterAmount = waterAmount;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Plot getPlot() {
        return plot;
    }

    public void setPlot(Plot plot) {
        this.plot = plot;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "taskId=" + taskId +
                ", cronExpression='" + cronExpression + '\'' +
                ", waterAmount=" + waterAmount +
                ", duration=" + duration +
                ", plot=" + plot +
                '}';
    }
}
