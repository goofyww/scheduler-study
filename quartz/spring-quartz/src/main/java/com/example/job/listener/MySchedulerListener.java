package com.example.job.listener;

import com.mysql.cj.jdbc.Driver;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.DataSourceConnectionFactory;
import org.quartz.listeners.SchedulerListenerSupport;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

public class MySchedulerListener extends SchedulerListenerSupport {

    @Override
    public void schedulerStarting() {
        super.schedulerStarting();
        LocalTime now = LocalTime.now();
        System.out.println(now.toString() + ",schedulerStarting!!");
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
            PreparedStatement preparedStatement = connection.prepareStatement("insert into quartz_scheduler_log (ip,type,time) values (?,?,?)");
            String ip = InetAddress.getLocalHost().getHostAddress();

            preparedStatement.setString(1, ip);
            preparedStatement.setInt(2, 1);
            preparedStatement.setTime(3, new Time(new Date().getTime()));
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            dataSource.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void schedulerShutdown() {
        super.schedulerShuttingdown();
        LocalTime now = LocalTime.now();
        System.out.println(now.toString() + ",schedulerShuttingdown!!");
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
            PreparedStatement preparedStatement = connection.prepareStatement("insert into quartz_scheduler_log (ip,type,time) values (?,?,?)");
            String ip = InetAddress.getLocalHost().getHostAddress();

            preparedStatement.setString(1, ip);
            preparedStatement.setInt(2, 2);
            preparedStatement.setTime(3, new Time(new Date().getTime()));
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            dataSource.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
