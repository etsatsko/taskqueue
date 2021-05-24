package com.webshop.simplewebapplication.database.Task;

import com.webshop.simplewebapplication.model.Task;

import java.util.List;

public interface TaskDAO {
    void addTask(Task task);
    List<Task> findAll();
    Task findById(int id);
    void deleteTask(Task task);
    int countOfTasks();
    void changeTask(Task task, String name, String description, int priority);
    void changeStatus(Task task, String status);
    List<Task> findAllFinished();
}
