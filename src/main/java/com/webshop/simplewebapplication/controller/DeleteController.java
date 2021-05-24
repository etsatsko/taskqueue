package com.webshop.simplewebapplication.controller;

import com.webshop.simplewebapplication.Service.TaskService;
import com.webshop.simplewebapplication.Service.BoardService;
import com.webshop.simplewebapplication.model.Board;
import com.webshop.simplewebapplication.model.BoardTask;
import com.webshop.simplewebapplication.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class DeleteController {

    @Autowired
    BoardService boardService;

    static final Logger logger = LoggerFactory.getLogger(DeleteController.class);

    @RequestMapping(path = "/task/{id}/delete", method = RequestMethod.POST)
    public ModelAndView delete(@PathVariable("id") int id) {
        TaskService taskService = new TaskService();
        Task task = taskService.findById(id);
        BoardTask bt = boardService.findByTask(task);
        boardService.deleteTaskFromBoard(bt);
        taskService.deleteTask(task);
        logger.info("Удалена задача");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("board");
        return modelAndView;
    }
}
