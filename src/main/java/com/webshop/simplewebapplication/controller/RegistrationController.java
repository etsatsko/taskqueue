package com.webshop.simplewebapplication.controller;

import com.webshop.simplewebapplication.Service.BoardService;
import com.webshop.simplewebapplication.Service.UserService;
import com.webshop.simplewebapplication.model.Board;
import com.webshop.simplewebapplication.model.Usr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

    static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private BoardService boardService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public ModelAndView add(@RequestParam("login") String login,
                            @RequestParam("password") String password)  {
        logger.info("Создан пользователь");
        ModelAndView modelAndView = new ModelAndView();
        if (userService.findByLogin(login) == null){
            Board board = new Board(0,login);
            boardService.createBoard(board);
            userService.addUser(new Usr(0,login,password, "USER"));
            logger.info("Created user with login: " + login);
            modelAndView.setViewName("login");
        } else{
            modelAndView.setViewName("registration");
            modelAndView.addObject("error", true);
        }
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
