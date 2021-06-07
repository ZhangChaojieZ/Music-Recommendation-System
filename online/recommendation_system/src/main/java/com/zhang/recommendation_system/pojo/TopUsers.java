package com.zhang.recommendation_system.pojo;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ZhangChaojie
 * @Description: TODO(相似音乐好友推荐)
 * @date 2021/4/26 21:54
 */
@Component
public class TopUsers {
    // 用户id
    private String name;
    // 相似好友id集合，中间用逗号隔开
    private String topUsers;
    // private List<User> topusers;

    public TopUsers() {
    }

    public String getName() {
        return name;
    }

    public TopUsers setName(String name) {
        this.name = name;
        return this;
    }

    public String getTopUsers() {
        return topUsers;
    }

    public TopUsers setTopUsers(String topUsers) {
        this.topUsers = topUsers;
        return this;
    }

    @Override
    public String toString() {
        return "TopUsers{" +
                "name='" + name + '\'' +
                ", topUsers='" + topUsers + '\'' +
                '}';
    }
}
