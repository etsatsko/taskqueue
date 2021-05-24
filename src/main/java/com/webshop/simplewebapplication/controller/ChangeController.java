package com.webshop.simplewebapplication.controller;

import com.webshop.simplewebapplication.Service.BoardService;
import com.webshop.simplewebapplication.Service.TaskService;
import com.webshop.simplewebapplication.model.Board;
import com.webshop.simplewebapplication.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ChangeController {

    static final Logger logger = LoggerFactory.getLogger(ChangeController.class);

    @Autowired
    TaskService taskService;
    @Autowired
    BoardService boardService;

    @PostMapping("/change")
    public ModelAndView change(@RequestParam("task") int id,
                               @RequestParam("name") String name,
                               @RequestParam("description") String description,
                               @RequestParam("priority") int priority)  {
        Task task = taskService.findById(id);
        taskService.changeTask(task, name, description, priority);
        logger.info("Изменена задача");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("change");
        return modelAndView;
    }

    @RequestMapping(path = "/change/status/finished/{id}", method = RequestMethod.POST)
    public ModelAndView sendFinished(@PathVariable("id") int id) {
        Task task = taskService.findById(id);
        taskService.changeStatus(task, "Finished");
        logger.info("Перенесено в завершенные");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("board");
        return modelAndView;
    }

    @RequestMapping(path = "/change/status/inprogress/{id}", method = RequestMethod.POST)
    public ModelAndView sendInprogress(@PathVariable("id") int id) {
        Task task = taskService.findById(id);
        taskService.changeStatus(task, "InProgress");
        logger.info("Перенесено в работу");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("board");
        return modelAndView;
    }

    @GetMapping("/change")
    public ModelAndView change(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        Board board = boardService.findBoardByName(currentUserName);
        List<Task> tasks = boardService.findAllInBoard(board);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("change");
        modelAndView.addObject("tasks", tasks);
        logger.info("Страница изменения");
        return modelAndView;
    }
}
