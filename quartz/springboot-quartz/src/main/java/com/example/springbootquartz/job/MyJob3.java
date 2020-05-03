package com.example.springbootquartz.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyJob3 {

    public void execute() {
        log.info("我是MyJob3！");
    }
}
