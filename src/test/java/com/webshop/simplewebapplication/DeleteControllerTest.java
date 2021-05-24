package com.webshop.simplewebapplication;

import com.webshop.simplewebapplication.controller.DeleteController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("etsatsko")
public class DeleteControllerTest {

    @Autowired
    private DeleteController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deleteTaskTest() throws Exception {
//        this.mockMvc.perform(post("/task/11/delete"))
//                .andDo(print())
//                .andExpect(xpath("//*[@id='fin']").string("Выполненные задачи"));
    }
}
