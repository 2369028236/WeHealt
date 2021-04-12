package com.guigu.pojo;

import java.io.Serializable;
import java.util.Date;

public class OrderSetting implements Serializable {
    private Integer id;

    private Date orderDate;

    private Integer number;

    private Integer reservations;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getReservations() {
        return reservations;
    }

    public void setReservations(Integer reservations) {
        this.reservations = reservations;
    }

    public OrderSetting(Date orderDate, Integer number) {
        this.id = id;
        this.orderDate = orderDate;
        this.number = number;
        this.reservations = reservations;
    }

    public OrderSetting() {
    }

    @Override
    public String toString() {
        return "OrderSetting{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", number=" + number +
                ", reservations=" + reservations +
                '}';
    }
}