package com.webshop.simplewebapplication;

import com.webshop.simplewebapplication.controller.ChangeController;
import com.webshop.simplewebapplication.controller.RegistrationController;
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
public class ChangeControllerTest {
    @Autowired
    private ChangeController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void changeTest() throws Exception {
        this.mockMvc.perform(post("/change")
                .param("task", "12")
                .param("name", "345")
                .param("description", "444")
                .param("priority", "1"))
                .andDo(print())
                .andExpect(xpath("//*[@id='ret']").string("Вернуться к доске"));
    }
}
