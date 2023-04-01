package com.example.demo.scheduler;

import com.example.demo.plot.Plot;
import jakarta.persistence.*;


@Entity
@Table
public class Schedule {

    public Schedule(Long taskId, String cronExpression, Integer waterAmount, Integer duration, Plot plot) {
        this.taskId = taskId;
        this.cronExpression = cronExpression;
        this.waterAmount = waterAmount;
        this.duration = duration;
        this.plot = plot;
    }

    @Id
    @Column(name = "Id")
    private Long taskId;

    private String cronExpression;
    private Integer waterAmount; //better for a double to be more precise
    private Integer duration; // in minutes

    @ManyToOne()
    @JoinColumn(name="plot_id", referencedColumnName = "id", nullable = false)
    private Plot plot;

    public Schedule() {
    }


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
