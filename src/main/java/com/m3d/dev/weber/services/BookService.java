/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3d.dev.weber.services;

import com.m3d.dev.weber.entities.Book;
import java.util.Optional;

/**
 *
 * @author m3d
 */
public interface BookService {

    public Iterable<Book> findAll();

    public Book create(Book book);

    public Optional<Book> find(String isbn);
    
    public Optional<Book> update(Book book);
}
