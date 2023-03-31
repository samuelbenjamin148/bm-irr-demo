package com.example.demo.scheduler;

public class ScheduleForm {

    private Long taskId;
    private String cronExpression;
    private Integer waterAmount;
    private Integer duration; // in seconds
    private Long plotId;

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

    public Long getPlotId() {
        return plotId;
    }

    public void setPlotId(Long plotId) {
        this.plotId = plotId;
    }


    @Override
    public String toString() {
        return "ScheduleForm{" +
                "taskId=" + taskId +
                ", cronExpression='" + cronExpression + '\'' +
                ", waterAmount=" + waterAmount +
                ", duration=" + duration +
                ", plotId=" + plotId +
                '}';
    }
}
