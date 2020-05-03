package com.example.job.listener;

import org.apache.commons.dbcp2.DataSourceConnectionFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;

import javax.sql.DataSource;
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

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        super.jobToBeExecuted(context);
        LocalTime now = LocalTime.now();
        System.out.println(now.toString() + "jobToBeExecuted");
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
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        super.jobWasExecuted(context, jobException);
        LocalTime now = LocalTime.now();
        System.out.println(now.toString() + "jobWasExecuted");
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
    }

}
