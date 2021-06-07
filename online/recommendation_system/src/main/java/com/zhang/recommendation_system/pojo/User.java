package com.zhang.recommendation_system.pojo;

import org.springframework.stereotype.Component;

/**
 * @author ZhangChaojie
 * @Description: TODO(用户实体类)
 * @date 2021/4/25 20:17
 */
@Component
public class User {
    // 用户id
    private String uid;
    // 用户名
    private String name;
    // 用户密码
    private String password;
    // 用户性别
    private String gender;
    // 用户年龄
    private int age;
    // 用户所在地区
    private String area;
    // 用户注册时间
    private String registerTime;
    // 用户的自我简介
    private String des;
    public User() {
    }

    public String getUid() {
        return uid;
    }

    public User setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public User setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public int getAge() {
        return age;
    }

    public User setAge(int age) {
        this.age = age;
        return this;
    }

    public String getArea() {
        return area;
    }

    public User setArea(String area) {
        this.area = area;
        return this;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public User setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
        return this;
    }

    public String getDes() {
        return des;
    }

    public User setDes(String des) {
        this.des = des;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", area='" + area + '\'' +
                ", registerTime='" + registerTime + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
