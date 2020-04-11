/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3d.dev.weber.services;

import com.m3d.dev.weber.entities.Order;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

/**
 *
 * @author m3d
 */
@Service
public class OrderService {

    private final List<Order> orders = new ArrayList<>();

    @PostConstruct
    public void setup() {
        createOrders();
    }

    public Iterable<Order> findAll() {
        return orders;
    }

    private Iterable<Order> createOrders() {
        for (int i = 0; i < 25; i++) {
            this.orders.add(createOrder());
        }
        return orders;
    }

    private Order createOrder() {
        String id = UUID.randomUUID().toString();
        double amount = ThreadLocalRandom.current().nextDouble(1000.00d);
        return new Order(id, BigDecimal.valueOf(amount));
    }
}
