package com.zcoox;

import com.zcoox.job.MyJob;
import com.zcoox.listener.MyScheduleListener;
import com.zcoox.listener.MyTriggerListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws SchedulerException {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.getListenerManager().addSchedulerListener(new MyScheduleListener());
        scheduler.start();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    scheduler.shutdown();
                } catch (SchedulerException e) {
                    e.printStackTrace();
                }
            }
        });
        Date date = DateBuilder.futureDate(15, DateBuilder.IntervalUnit.SECOND);

//        JobDetail jobDetail1 = JobBuilder.newJob(MyJob.class)
//                .withIdentity("myJobDetail1", "myGroup1")
//                .build();
//
//        Trigger trigger1 = TriggerBuilder.newTrigger()
//                .startAt(date)
//                .withPriority(2)
//                .usingJobData("msg", "我是trigger1触发的")
//                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(15).repeatForever())
//                .build();

        JobDetail jobDetail2 = JobBuilder.newJob(MyJob.class)
                .withIdentity("myJobDetail2", "myGroup2")
//                .usingJobData("name", "tom")
                .build();

//        Trigger trigger2 = TriggerBuilder.newTrigger()
//                .startNow()
//                .withPriority(9)
//                .usingJobData("msg", "我是trigger2触发的")
//                .withSchedule(SimpleScheduleBuilder
//                        .simpleSchedule()
//                        .withIntervalInSeconds(5)
//                        .withRepeatCount(4))
//                .endAt(DateBuilder.futureDate(15, DateBuilder.IntervalUnit.SECOND))
//                .build();

        Trigger trigger2 = TriggerBuilder.newTrigger()
                .startNow()
                .withPriority(9)
                .usingJobData("msg", "我是trigger2触发的")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")
//                        .withMisfireHandlingInstructionDoNothing()
//                        .withMisfireHandlingInstructionFireAndProceed()
                                .withMisfireHandlingInstructionIgnoreMisfires()
                )
                .endAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.HOUR))
                .build();

//        scheduler.scheduleJob(jobDetail1, trigger1);
        scheduler.getListenerManager().addTriggerListener(new MyTriggerListener());
//        scheduler.getListenerManager().addJobListener(new MyJobListener());
        scheduler.scheduleJob(jobDetail2, trigger2);
        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduler.shutdown();
    }
}
