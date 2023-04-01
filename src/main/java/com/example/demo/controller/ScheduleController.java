package com.example.demo.controller;


import com.example.demo.scheduler.Schedule;
import com.example.demo.scheduler.ScheduleForm;
import com.example.demo.scheduler.SchedulerBean;
import com.example.demo.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/scheduler")
public class ScheduleController {

    @Autowired
    private ScheduleService taskSchedulingService;


    @GetMapping
    public List<Schedule> getSchedules() {
        return taskSchedulingService.getSchedules();
    }

    @PostMapping(path="/schedule" , consumes = "application/json", produces="application/json")
    public void scheduleATask(@RequestBody ScheduleForm schedule) {
        SchedulerBean taskBean = new SchedulerBean();
        taskBean.setTaskDefinition(schedule);
        taskSchedulingService.scheduleATask(schedule, taskBean);
    }

    @GetMapping(path="/unschcedule/{jobId}")
    public void removeJob(@PathVariable Long jobId) {
        taskSchedulingService.removeScheduledTask(jobId);
    }
}
