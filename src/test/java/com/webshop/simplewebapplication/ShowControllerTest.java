package com.webshop.simplewebapplication;

import com.webshop.simplewebapplication.controller.ShowController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("etsatsko")
public class ShowControllerTest {

    @Autowired
    private ShowController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void taskListTest() throws Exception {
        this.mockMvc.perform(get("/board"))
                .andDo(print())
                .andExpect(xpath("//div[@id='task-list']/div").nodeCount(3));
    }
}
