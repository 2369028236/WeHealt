package com.guigu.pojo;

import java.io.Serializable;
import java.util.Date;

public class OrderAndMember implements Serializable {
    private Integer id;

    private Integer member_id;
    private Integer order_id;


    private Date orderDate;

    private String orderType;

    private String orderStatus;

    private Integer setmeal_id;

    private String fileNumber;

    private String memberName;

    private String sex;

    private String idCard;

    private String phoneNumber;

    private Date regTime;

    private String password;

    private String email;

    private Date birthday;

    private String remark;

    private String SetmealName;

    public OrderAndMember(Integer id, Integer member_id, Date orderDate, String orderType, String orderStatus, Integer setmeal_id, String fileNumber, String memberName, String sex, String idCard, String phoneNumber, Date regTime, String password, String email, Date birthday, String remark, String setmealName) {
        this.id = id;
        this.member_id = member_id;
        this.orderDate = orderDate;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.setmeal_id = setmeal_id;
        this.fileNumber = fileNumber;
        this.memberName = memberName;
        this.sex = sex;
        this.idCard = idCard;
        this.phoneNumber = phoneNumber;
        this.regTime = regTime;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.remark = remark;
        SetmealName = setmealName;
    }

    public OrderAndMember() {
    }

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
        this.orderType = orderType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getSetmeal_id() {
        return setmeal_id;
    }

    public void setSetmeal_id(Integer setmeal_id) {
        this.setmeal_id = setmeal_id;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getName() {
        return memberName;
    }

    public void setName(String memberName) {
        this.memberName = memberName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSetmealName() {
        return SetmealName;
    }

    public void setSetmealName(String setmealName) {
        SetmealName = setmealName;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Override
    public String toString() {
        return "OrderAndMember{" +
                "id=" + id +
                ", member_id=" + member_id +
                ", orderDate=" + orderDate +
                ", orderType='" + orderType + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", setmeal_id=" + setmeal_id +
                ", fileNumber='" + fileNumber + '\'' +
                ", memberName='" + memberName + '\'' +
                ", sex='" + sex + '\'' +
                ", idCard='" + idCard + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", regTime=" + regTime +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", remark='" + remark + '\'' +
                ", SetmealName='" + SetmealName + '\'' +
                '}';
    }
}
