package com.guigu.pojo;

import java.io.Serializable;
import java.util.Date;

public class UserAndOrder implements Serializable {
    private Integer id;

    private Date birthday;

    private String gender;

    private String username;

    private String password;

    private String user_remark;

    private String station;

    private String telephone;

    private Integer user_id;

    private Integer order_id;

    private Integer member_id;

    private Date orderDate;

    private String orderType;

    private String orderStatus;

    private Integer setmeal_id;

    private String name;

    private String code;

    private String helpCode;

    private String sex;

    private String age;

    private Float price;

    private String remark;

    private String attention;

    private String img;

    public UserAndOrder(Integer id, Date birthday, String gender, String username, String password, String user_remark, String station, String telephone, Integer user_id, Integer order_id, Integer member_id, Date orderDate, String orderType, String orderStatus, Integer setmeal_id, String name, String code, String helpCode, String sex, String age, Float price, String setMeal_remark, String attention, String img) {
        this.id = id;
        this.birthday = birthday;
        this.gender = gender;
        this.username = username;
        this.password = password;
        this.user_remark = user_remark;
        this.station = station;
        this.telephone = telephone;
        this.user_id = user_id;
        this.order_id = order_id;
        this.member_id = member_id;
        this.orderDate = orderDate;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.setmeal_id = setmeal_id;
        this.name = name;
        this.code = code;
        this.helpCode = helpCode;
        this.sex = sex;
        this.age = age;
        this.price = price;
        this.remark = remark;
        this.attention = attention;
        this.img = img;
    }

    public UserAndOrder() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHelpCode() {
        return helpCode;
    }

    public void setHelpCode(String helpCode) {
        this.helpCode = helpCode;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getUser_remark() {
        return user_remark;
    }

    public void setUser_remark(String user_remark) {
        this.user_remark = user_remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "UserAndOrder{" +
                "id=" + id +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", user_remark='" + user_remark + '\'' +
                ", station='" + station + '\'' +
                ", telephone='" + telephone + '\'' +
                ", user_id=" + user_id +
                ", order_id=" + order_id +
                ", member_id=" + member_id +
                ", orderDate=" + orderDate +
                ", orderType='" + orderType + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", setmeal_id=" + setmeal_id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", helpCode='" + helpCode + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", price=" + price +
                ", setMeal_remark='" + remark + '\'' +
                ", attention='" + attention + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
