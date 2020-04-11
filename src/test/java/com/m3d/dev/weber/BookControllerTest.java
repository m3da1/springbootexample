/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3d.dev.weber;

import com.jayway.jsonpath.Option;
import com.m3d.dev.weber.controllers.BooksController;
import com.m3d.dev.weber.entities.Book;
import com.m3d.dev.weber.services.BookService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.hasSize;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 *
 * @author m3d
 */
@SpringBootTest
//@RunWith(SpringRunner.class)
//@WebMvcTest(BooksController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void shouldReturnListofBooks() throws Exception {
        when(bookService.findAll()).thenReturn(Arrays.asList(
                new Book("123", "Spring 5 Recipes", "Marten Deinum", "Josh Long"),
                new Book("321", "Pro Spring MVC", "Marten Deinum", "Colin Yates")));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].isbn", Matchers.containsInAnyOrder("123", "321")))
                .andExpect(jsonPath("$[*].title", Matchers.containsInAnyOrder("Spring 5 Recipes", "Pro Spring MVC")));
    }

    @Test
    public void shouldReturn404WhenBookNotFound() throws Exception {
        when(bookService.find(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());
        mockMvc.perform(get("/books/123"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnBookWhenFound() throws Exception {
        when(bookService.find(ArgumentMatchers.anyString())).thenReturn(
                Optional.of(
                        new Book("123", "Spring 5 Recipes", "Marten Deinum", "Josh Long")));

        mockMvc.perform(get("/books/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn", Matchers.equalTo("123")))
                .andExpect(jsonPath("$.title", Matchers.equalTo("Spring 5 Recipes")));
    }
}
