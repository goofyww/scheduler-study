package com.example.job.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.listeners.TriggerListenerSupport;

import java.time.LocalTime;

public class MyListener extends TriggerListenerSupport {

    @Override
    public String getName() {
        return "myTriggerListener";
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        super.triggerFired(trigger, context);
        LocalTime now = LocalTime.now();
        System.out.println(now.toString() + "Trigger is fired!!");
    }
    
}
