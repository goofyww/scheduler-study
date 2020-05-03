package com.zcoox.boot;

import com.zcoox.boot.elasticjob.annotation.EnableElasticJob;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableElasticJob
public class BootElasticJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootElasticJobApplication.class, args);
    }

}
