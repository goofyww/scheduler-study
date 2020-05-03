package com.example.springbootquartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.listeners.TriggerListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class MyTriggerListener extends TriggerListenerSupport {

    @Override
    public String getName() {
        return "myTriggerListener";
    }
    
    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        super.triggerFired(trigger, context);
        System.out.println("Trigger is fired!!!");
    }
}
