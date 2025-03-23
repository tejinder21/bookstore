package com.example.bookstore;

import com.example.bookstore.web.CategoryController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCategoryListPageLoads() throws Exception {
        mockMvc.perform(get("/categorylist"))
                .andExpect(status().isOk());
    }

    @Test
    void testAddCategoryPageLoads() throws Exception {
        mockMvc.perform(get("/addcategory"))
                .andExpect(status().isOk());
    }
}