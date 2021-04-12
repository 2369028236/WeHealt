package com.guigu.pojo;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private Integer id;

    private Integer member_id;

    private Date orderDate;

    private String orderType;

    private String orderStatus;

    private Integer setmeal_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMember_id() {
        return member_id;
    }

    public void setMember_id(Integer member_id) {
        this.member_id = member_id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public Integer getSetmeal_id() {
        return setmeal_id;
    }

    public void setSetmeal_id(Integer setmeal_id) {
        this.setmeal_id = setmeal_id;
    }

    public Order(Integer id, Integer member_id, Date orderDate, String orderType, String orderStatus, Integer setmeal_id) {
        this.id = id;
        this.member_id = member_id;
        this.orderDate = orderDate;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.setmeal_id = setmeal_id;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", member_id=" + member_id +
                ", orderDate=" + orderDate +
                ", orderType='" + orderType + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", setmeal_id=" + setmeal_id +
                '}';
    }
}