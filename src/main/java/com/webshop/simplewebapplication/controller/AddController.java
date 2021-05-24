package com.webshop.simplewebapplication.controller;

import com.webshop.simplewebapplication.Service.TaskService;
import com.webshop.simplewebapplication.Service.BoardService;
import com.webshop.simplewebapplication.model.Board;
import com.webshop.simplewebapplication.model.BoardTask;
import com.webshop.simplewebapplication.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AddController {

    @Autowired
    TaskService taskService;
    @Autowired
    BoardService boardService;

    static final Logger logger = LoggerFactory.getLogger(AddController.class);

    @PostMapping("/add")
    public ModelAndView add(@RequestParam("name") String name,
                            @RequestParam("description") String description,
                            @RequestParam("priority") int priority)  {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        Board board = boardService.findBoardByName(currentUserName);
        Task task = new Task(0, name,description,priority,"InProgress");
        taskService.addTask(task);
        boardService.addTaskToBoard(new BoardTask(0, task, board));
        logger.info("Добавлена задача");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addTask");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addTask");
        logger.info("Страница добавления");
        return modelAndView;
    }
}
