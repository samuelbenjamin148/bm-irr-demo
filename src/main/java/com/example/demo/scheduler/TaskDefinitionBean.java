package com.example.demo.scheduler;

import org.springframework.stereotype.Service;

@Service
public class TaskDefinitionBean implements Runnable {

    private ScheduleForm schedule;



    public void run() {

        System.out.println("Running action:  " + schedule.toString());


    }

    public ScheduleForm getTaskDefinition() {

        return schedule;
    }

    public void setTaskDefinition(ScheduleForm schedule) {
        this.schedule = schedule;
    }
}