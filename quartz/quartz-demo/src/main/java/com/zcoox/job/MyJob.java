package com.zcoox.job;

import org.quartz.*;

import java.time.LocalDateTime;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class MyJob implements Job {

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
//        String name = (String) dataMap.get("name");
//        System.out.println(LocalDateTime.now() + ":" + name + ":我正在执行");

        // JobDataMap的更新
//        count++;
//        jobExecutionContext.getJobDetail().getJobDataMap().put("count", count);

        String msg = (String) jobExecutionContext.getTrigger().getJobDataMap().get("msg");
        System.out.println(LocalDateTime.now().toString() + ":msg=" + msg);
//        try {
//            Thread.sleep(7000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
