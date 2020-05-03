package com.zcoox.mmalljob.order;

import com.zcoox.boot.elasticjob.annotation.EnableElasticJob;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication(exclude = {
        SpringDataWebAutoConfiguration.class,
        RedisAutoConfiguration.class,
        DataSourceAutoConfiguration.class,
})
@MapperScan(basePackages = {"com.zcoox.mmalljob.**.dao"})
@ImportResource({"classpath:applicationContext.xml"})
@EnableElasticJob
public class OrderServiceApplication {

    private static final CountDownLatch latch = new CountDownLatch(1);

    /**
     * 启动类main方法
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(OrderServiceApplication.class, args).registerShutdownHook();
        latch.await();
    }

}
