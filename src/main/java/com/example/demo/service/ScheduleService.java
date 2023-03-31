package com.example.demo.service;

import com.example.demo.plot.Plot;
import com.example.demo.repository.PlotRepository;
import com.example.demo.repository.ScheduleRepository;
import com.example.demo.scheduler.Schedule;
import com.example.demo.scheduler.ScheduleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ScheduledFuture;

@Service
public class ScheduleService {

    @Autowired
    private TaskScheduler taskScheduler;
    private final PlotRepository plotRepository;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(
            PlotRepository plotRepository,
            ScheduleRepository scheduleRepository) {
        this.plotRepository = plotRepository;
        this.scheduleRepository = scheduleRepository;
    }

    Map<Long, ScheduledFuture<?>> jobsMap = new HashMap<>();

    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }

    @Transactional
    public void scheduleATask(ScheduleForm scheduleForm, Runnable tasklet) {
        System.out.println(
                "Scheduling task with job id: "
                        + scheduleForm.toString());

        Optional<Schedule> scheduleOptional = scheduleRepository.findById(scheduleForm.getTaskId());
        if(scheduleOptional.isPresent()) {
            throw new IllegalStateException("Schedule Task ID " + scheduleForm.getTaskId()
            + " already exist");
        }

        Optional<Plot> plotOptional = plotRepository.findById(scheduleForm.getPlotId());
        if(!plotOptional.isPresent()) {
            throw new IllegalStateException("Plot ID " + scheduleForm.getPlotId()
                    + " does not exist");
        }


        //schedule the task based on the cronJob
        ScheduledFuture<?> scheduledTask =
                taskScheduler.schedule(
                        tasklet,
                        new CronTrigger(scheduleForm.getCronExpression(), TimeZone.getTimeZone(TimeZone.getDefault().getID())));
        jobsMap.put(scheduleForm.getTaskId(), scheduledTask);

        //create new Schedule entry
        Schedule mSchedule = new Schedule();
        mSchedule.setTaskId(scheduleForm.getTaskId());
        mSchedule.setCronExpression(scheduleForm.getCronExpression());
        mSchedule.setWaterAmount(scheduleForm.getWaterAmount());
        mSchedule.setDuration(scheduleForm.getDuration());
        mSchedule.setPlot(plotOptional.get());
        scheduleRepository.save(mSchedule);


    }

    @Transactional
    public void removeScheduledTask(Long jobId) {
        ScheduledFuture<?> scheduledTask = jobsMap.get(jobId);
        if(scheduledTask == null) {
            throw new IllegalStateException("Task ID " + jobId
                    + " does not exist");
        }
        scheduledTask.cancel(true);
        jobsMap.put(jobId, null);
        scheduleRepository.removeSchdulebyID(jobId);
    }
}