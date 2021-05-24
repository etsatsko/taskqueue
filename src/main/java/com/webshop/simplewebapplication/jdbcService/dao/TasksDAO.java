package com.webshop.simplewebapplication.jdbcService.dao;

import com.webshop.simplewebapplication.jdbcService.executor.Executor;
import com.webshop.simplewebapplication.model.Task;
import com.webshop.simplewebapplication.model.Usr;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TasksDAO {

    private Executor executor;

    public TasksDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public List<Task> findAllFinished() throws SQLException {
        List<Task> ts =  executor.execQuery("select distinct * from task where status='Finished'", result -> {
            ArrayList<Task> tasks = new ArrayList<>();
            while(result.next()){
                int id = result.getInt(1);
                String name = result.getString(2);
                String description = result.getString(3);
                int priority = result.getInt(4);
                String status = result.getString(5);
                tasks.add(new Task(id, name, description, priority, status));

             }
            return tasks;
        });
        return ts;
    }

    public boolean checkUserExists(String login) throws SQLException {
        return executor.execQuery("select exists (select * from usr where login='" + login + "')", result -> {
            result.next();
            return result.getBoolean(1);
        });
    }

    public void insertTask(String name, String description, int priority, String status) throws SQLException {
        executor.execUpdate("insert into task (name, description, priority, status) values ('" + name + "', '" + description + "', '" + priority + "', '" + status + "')");
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists task (id bigint auto_increment primary key, " +
                "name varchar(255), description varchar(255), priority bigint, status varchar(255))");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("drop table task");
    }
}
