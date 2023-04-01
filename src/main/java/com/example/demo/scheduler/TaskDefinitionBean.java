package com.example.demo.scheduler;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TaskDefinitionBean implements Runnable {

    private ScheduleForm schedule;



    public void run() {

        System.out.println("Running action:  " + schedule.toString());
        //TODO: make this more dynamic
        final String uri = "http://localhost:8080/api/sensor/irrigate/"
                + schedule.getPlotId() + "/"
                + schedule.getWaterAmount() + "/"
                + schedule.getDuration();
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        System.out.print(result);

    }

    public ScheduleForm getTaskDefinition() {
        return schedule;
    }

    public void setTaskDefinition(ScheduleForm schedule) {
        this.schedule = schedule;
    }
}