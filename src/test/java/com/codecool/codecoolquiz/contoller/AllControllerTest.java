package com.codecool.codecoolquiz.contoller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class AllControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void categoryResponseAccessDenied() throws Exception {
        mockMvc. perform(get("/categories"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void typeResponseAccessDenied() throws Exception {
        mockMvc.perform(get("/types"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void customQuizResponseAccessDenied() throws Exception {
        mockMvc.perform(get("/customquizzes"))
                .andExpect(status().is4xxClientError());
    }

//    @Test
//    public void categoryResponseNotEmpty() throws Exception {
//        mockMvc. perform(get("/categories"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").isNotEmpty());
//    }
//
//    @Test
//    public void typeResponseNotEmpty() throws Exception {
//        mockMvc.perform(get("/types"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").isNotEmpty());
//    }
//
//    @Test
//    public void customQuizResponseCustomQuizzezNotEmpty() throws Exception {
//        mockMvc.perform(get("/customquizzes"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").isNotEmpty());
//    }

}
