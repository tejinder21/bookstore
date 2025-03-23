package com.example.bookstore;

import com.example.bookstore.web.BookController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testBookListPageLoads() throws Exception {
        mockMvc.perform(get("/booklist"))
                .andExpect(status().isOk());
    }

    @Test
    void testAddBookPageLoads() throws Exception {
        mockMvc.perform(get("/add"))
                .andExpect(status().isOk());
    }

    @Test
    void testLoginPageLoads() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }
}