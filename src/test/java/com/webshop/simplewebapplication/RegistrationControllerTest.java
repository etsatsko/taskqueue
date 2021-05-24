package com.webshop.simplewebapplication;

import com.webshop.simplewebapplication.controller.RegistrationController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerTest {
    @Autowired
    private RegistrationController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void registrationTest() throws Exception {
        this.mockMvc.perform(post("/registration")
                .param("login", "new_user" + new Random().nextInt(500))
                .param("password", "12345"))
                .andDo(print())
                .andExpect(xpath("//*[@id='reg']").string("Регистрация"));
    }
}
