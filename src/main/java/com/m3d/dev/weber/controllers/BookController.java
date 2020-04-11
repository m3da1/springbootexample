/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3d.dev.weber.controllers;

import com.m3d.dev.weber.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author m3d
 */
@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService service) {
        this.bookService = service;
    }

    @GetMapping("/books.html")
    public String all(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/list";
    }

    @GetMapping(value = "/books.html", params = "isbn")
    public String get(@RequestParam("isbn") String isbn, Model model) {
        bookService.find(isbn)
                .ifPresent(book -> model.addAttribute("book", book));
        return "books/details";
    }
    
    @GetMapping("/books/500")
    public void error() {
        throw new NullPointerException("Dummy Null Pointer Exception");
    }

}
