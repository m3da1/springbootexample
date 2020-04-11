/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3d.dev.weber.controllers;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author m3d
 */
@RestController
@RequestMapping("/async")
public class IndexController {

    private final TaskExecutor taskExectuExecutor;

    public IndexController(TaskExecutor executor) {
        this.taskExectuExecutor = executor;
    }

    @GetMapping("/")
    public Callable<String> sayHello() {
        return () -> {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
            return "Async Hello world";
        };
    }
    
    @GetMapping
    public CompletableFuture<String> completableHello() {
        return CompletableFuture.supplyAsync(() -> {
            randomDelay();
            return "CompletableFuture says Hello world spring boot 2!";
        }, taskExectuExecutor);
    }

    private void randomDelay() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
