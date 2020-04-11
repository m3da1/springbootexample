package com.m3d.dev.weber;

import com.m3d.dev.weber.entities.Book;
import com.m3d.dev.weber.services.BookService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WeberApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeberApplication.class, args);
    }

    @Bean
    public CustomizedErrorAttributes errorAttributes() {
        return new CustomizedErrorAttributes();
    }

    @Bean
    public ApplicationRunner booksInitializer(BookService bookService) {
        return args -> {
            bookService.create(new Book("9780061120084", "To Kill a Mockingbird", "Harper Lee"));
            bookService.create(new Book("9780451524935", "1984", "George Orwell"));
            bookService.create(new Book("9780618260300", "The Hobbit", "J.R.R. Tolkien"));
        };
    }

}
