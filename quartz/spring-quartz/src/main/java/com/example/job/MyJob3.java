package com.example.job;

import java.time.LocalTime;

public class MyJob3 {

    public void execute() {
        LocalTime now = LocalTime.now();
        System.out.println(now.toString() + "，我MyJob3正在执行！");
    }
}
