package com.zcoox.listener;

import org.quartz.listeners.SchedulerListenerSupport;

import java.time.LocalTime;

public class MyScheduleListener extends SchedulerListenerSupport {

    @Override
    public void schedulerStarting() {
        super.schedulerStarting();
        LocalTime now = LocalTime.now();
        System.out.println(now.toString() + ",schedulerStarting!!");
    }

    @Override
    public void schedulerShutdown() {
        super.schedulerShutdown();
        LocalTime now = LocalTime.now();
        System.out.println(now.toString() + ",schedulerShuttingdown!!");
    }
}
