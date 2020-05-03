package com.zcoox.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.listeners.TriggerListenerSupport;

import java.time.LocalTime;
import java.util.Calendar;

public class MyTriggerListener extends TriggerListenerSupport {

    @Override
    public String getName() {
        return "myTriggerListener";
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        super.triggerFired(trigger, context);
        System.out.println(LocalTime.now().toString() + ":Trigger执行之前");
    }

    @Override
    public void triggerComplete(
            Trigger trigger,
            JobExecutionContext context,
            Trigger.CompletedExecutionInstruction triggerInstructionCode) {
        super.triggerComplete(trigger, context, triggerInstructionCode);
        System.out.println(LocalTime.now().toString() + ":Trigger执行之后");
    }

    /**
     * 如果时间 秒是10 的时间，本次触发则不会生效
     *
     * @param trigger
     * @param context
     * @return
     */
    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        Calendar calendar = Calendar.getInstance();
        int second = calendar.get(Calendar.SECOND);
        if (second == 10) {
            System.out.println("本次Trigger不触发");
            return true;
        }
        return false;
    }

}
