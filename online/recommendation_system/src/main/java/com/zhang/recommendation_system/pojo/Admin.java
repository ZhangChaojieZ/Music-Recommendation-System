package com.zhang.recommendation_system.pojo;

import org.springframework.stereotype.Component;

/**
 * @author ZhangChaojie
 * @Description: TODO(管理员实体类)
 * @date 2021/4/25 20:17
 */
@Component
public class Admin {
    // 管理员账号
    private String name;
    // 管理员密码
    private String password;

    public Admin() {
    }

    public String getName() {
        return name;
    }

    // 类似链式编程
    public Admin setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Admin setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
