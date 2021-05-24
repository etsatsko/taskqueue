package com.webshop.simplewebapplication;

import com.webshop.simplewebapplication.Service.TaskService;
import com.webshop.simplewebapplication.database.Task.TaskDAOHib;
import com.webshop.simplewebapplication.model.Task;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskDAOHib dataBase;

    @Test
    public void addTask() {
        Task task = new Task(0, "Решить уравнение", "Решить ур-е с помощью Эйлера", 2, "InProgress");
        boolean isItemCreated = taskService.addTask(task);
        Assert.assertTrue(isItemCreated);
        Mockito.verify(dataBase, Mockito.times(1)).addTask(task);
    }

    @Test
    public void deleteTask() {
        Task task = taskService.findById(1);
        boolean isItemDeleted = taskService.deleteTask(task);
        Assert.assertTrue(isItemDeleted);
        Mockito.verify(dataBase, Mockito.times(1)).deleteTask(task);
    }
}
