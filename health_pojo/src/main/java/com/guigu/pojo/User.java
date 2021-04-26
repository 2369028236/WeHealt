package com.guigu.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {
    private Integer id;

    private Date birthday;

    private String gender;

    private String username;

    private String password;

    private String user_remark;

    private String station;

    private String telephone;

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        this.gender = gender == null ? null : gender.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRemark() {
        return user_remark;
    }

    public void setRemark(String user_remark) {
        this.user_remark = user_remark == null ? null : user_remark.trim();
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station == null ? null : station.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public User(Integer id, Date birthday, String gender, String username, String password, String user_remark, String station, String telephone, String email) {
        this.id = id;
        this.birthday = birthday;
        this.gender = gender;
        this.username = username;
        this.password = password;
        this.user_remark = user_remark;
        this.station = station;
        this.telephone = telephone;
        this.email = email;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", user_remark='" + user_remark + '\'' +
                ", station='" + station + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    private Set<Role> roles = new HashSet<Role>(0);//对应角色集合
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}