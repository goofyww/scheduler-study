package com.zcoox.listener;

import com.mysql.cj.jdbc.Driver;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.DataSourceConnectionFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

public class MyJobListener extends JobListenerSupport {

    @Override
    public String getName() {
        return "myJobListener";
    }

    /**
     * Job即将执行
     *
     * @param context
     */
    public void jobToBeExecuted(JobExecutionContext context) {
        super.jobToBeExecuted(context);
        LocalTime now = LocalTime.now();
        System.out.println(now.toString() + "jobToBeExecuted");

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setUrl("jdbc:mysql://localhost:3306/order?serverTimezone=Asia/Shanghai&useSSL=false");
        try {
            dataSource.setDriver(new Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DataSourceConnectionFactory connectionFactory = new DataSourceConnectionFactory(dataSource);
        try {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into quartz_job_log (job_name,type,time) values (?,?,?)");
            preparedStatement.setString(1, context.getJobDetail().getKey().toString());
            preparedStatement.setInt(2, 1);
            preparedStatement.setTime(3, new Time(new Date().getTime()));
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            dataSource.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Trigger被否决，触发jobExecutionVetoed
     *
     * @param context
     */
    public void jobExecutionVetoed(JobExecutionContext context) {
        super.jobExecutionVetoed(context);
        System.out.println("Job不会执行(Trigger 被否决,执行此方法)");
    }

    /**
     * Job执行完毕
     *
     * @param context
     * @param jobException
     */
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        super.jobWasExecuted(context, jobException);
        LocalTime now = LocalTime.now();
        System.out.println(now.toString() + "jobWasExecuted");

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setUrl("jdbc:mysql://localhost:3306/order?serverTimezone=Asia/Shanghai&useSSL=false");
        try {
            dataSource.setDriver(new Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DataSourceConnectionFactory connectionFactory = new DataSourceConnectionFactory(dataSource);
        try {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into quartz_job_log (job_name,type,time) values (?,?,?)");
            preparedStatement.setString(1, context.getJobDetail().getKey().toString());
            preparedStatement.setInt(2, 2);
            preparedStatement.setTime(3, new Time(new Date().getTime()));
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            dataSource.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
