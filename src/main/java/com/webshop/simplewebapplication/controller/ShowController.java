package com.webshop.simplewebapplication.controller;

import com.webshop.simplewebapplication.Service.TaskService;
import com.webshop.simplewebapplication.Service.BoardService;
import com.webshop.simplewebapplication.jdbcService.DBException;
import com.webshop.simplewebapplication.jdbcService.DBService;
import com.webshop.simplewebapplication.model.Board;
import com.webshop.simplewebapplication.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class ShowController {

    static final Logger logger = LoggerFactory.getLogger(ShowController.class);

    @Autowired
    BoardService boardService;
    @Autowired
    TaskService taskService;
    @Autowired
    DBService dbService;

    @GetMapping("/")
    public ModelAndView index() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        Board board = boardService.findBoardByName(currentUserName);
        List<Task> tasks = boardService.getInProgress(board);
        logger.info("Выведены все задачи в работе");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("tasks", tasks);
        return modelAndView;
    }

    @GetMapping("/finishedTasks")
    public ModelAndView finished() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        Board board = boardService.findBoardByName(currentUserName);
        List<Task> tasks = boardService.getFinished(board);
        logger.info("Завершенные задачи");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("finishedTasks");
        modelAndView.addObject("tasks", tasks);
        return modelAndView;
    }

    @GetMapping("/board")
    public ModelAndView board() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        Board board = boardService.findBoardByName(currentUserName);
        List<Task> tasks = boardService.getInProgress(board);

        logger.info("Все задания в работе для доски");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("board");
        modelAndView.addObject("tasks", tasks);
        return modelAndView;
    }

    @GetMapping("/admin")
    public ModelAndView admin() throws DBException {
//        List<Task> tasks = taskService.findAllFinished();
        List<Task> tasks = dbService.findAllFinished();
        logger.info("Все выполненные задания пользователей");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        modelAndView.addObject("tasks", tasks);
        modelAndView.addObject("count", tasks.size());
        return modelAndView;
    }

    @GetMapping("/board/sorted")
    public ModelAndView sort() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        Board board = boardService.findBoardByName(currentUserName);
        List<Task> tasks = boardService.getTasksByPriority(board);

        logger.info("Отсортированные задания пользователя");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sorted");
        modelAndView.addObject("tasks", tasks);
        return modelAndView;
    }
}
