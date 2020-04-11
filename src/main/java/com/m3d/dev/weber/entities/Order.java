/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3d.dev.weber.entities;

import java.math.BigDecimal;

/**
 *
 * @author m3d
 */
public class Order {

    private String id;
    private BigDecimal amount;

    public Order() {

    }

    public Order(String id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("Order [id='%s', amount=%4.2f]", id, amount);
    }

}
