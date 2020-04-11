/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3d.dev.weber.services;

import com.m3d.dev.weber.entities.Book;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

/**
 *
 * @author m3d
 */
@Service
public class InMemoryBookService implements BookService {

    private final Map<String, Book> books = new ConcurrentHashMap<>();

    @Override
    public Iterable<Book> findAll() {
        return books.values();
    }

    @Override
    public Book create(Book book) {
        books.put(book.getIsbn(), book);
        return book;
    }

    @Override
    public Optional<Book> find(String isbn) {
        return Optional.ofNullable(books.get(isbn));
    }

    @Override
    public Optional<Book> update(Book book) {
        Book oldbook = books.get(book.getIsbn());
        if (oldbook != null) {
            oldbook.setAuthors(book.getAuthors());
            oldbook.setTitle(book.getTitle());
            books.put(oldbook.getIsbn(), oldbook);
            if (books.replace(oldbook.getIsbn(), oldbook, book)) {
                return Optional.of(oldbook);
            }
        }
//        books.computeIfPresent(book.getIsbn(), (String k, Book v) -> {
//            v.setAuthors(book.getAuthors());
//            v.setTitle(book.getTitle());
//            return v;
//        });
        return Optional.empty();
    }

}
