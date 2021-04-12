package com.guigu.pojo;

import java.io.Serializable;

public class CheckItem implements Serializable {
    private Integer id;

    private String code;

    private String name;

    private String sex;

    private String age;

    private Float price;

    private String type;

    private String attention;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention == null ? null : attention.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public CheckItem(Integer id, String code, String name, String sex, String age, Float price, String type, String attention, String remark) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.price = price;
        this.type = type;
        this.attention = attention;
        this.remark = remark;
    }

    public CheckItem() {
    }

    @Override
    public String toString() {
        return "CheckItem{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", attention='" + attention + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}