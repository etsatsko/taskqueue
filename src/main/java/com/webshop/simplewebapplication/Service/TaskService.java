package com.webshop.simplewebapplication.Service;

import com.webshop.simplewebapplication.database.Task.TaskDAOHib;
import com.webshop.simplewebapplication.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskDAOHib dataBase = new TaskDAOHib();

    public boolean addTask(Task task) {
        dataBase.addTask(task);
        return true;
    }

    public List<Task> findAll() {
        return dataBase.findAll();
    }


    public Task findById(int id) {
        return dataBase.findById(id);
    }

    public int countOfTasks(){
        return dataBase.countOfTasks();
    }

    public boolean deleteTask(Task task) {
        dataBase.deleteTask(task);
        return true;
    }

    public void changeTask(Task task, String name, String description, int priority) {
        dataBase.changeTask(task, name, description, priority);
    }
    public void changeStatus(Task task,String status) {
        dataBase.changeStatus(task, status);
    }

    public List<Task> findAllFinished() {
        return dataBase.findAllFinished();
    }
}
