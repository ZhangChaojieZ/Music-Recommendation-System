package com.zhang.recommendation_system.dao;

import com.zhang.recommendation_system.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    /**
     * 根据用户id查找用户
     * @param uid 用户id
     * @return 用户实例，User类型
     */
    User queryById(@Param("uid") String uid);

    /**
     * 查找所有用户信息
     * @return 用户实例集合，List<User>类型
     */
    List<User> queryAll();

    /**
     * 添加用户
     * @param user 需要添加的用户
     */
    void addUser(User user);

    /**
     * 更新用户信息
     * @param user 新的用户信息
     */
    void updateUser(User user);

    /**
     * 更新密码
     * @param uid 用户id
     * @param newPs 新密码
     */
    void updatePs(@Param("uid") String uid, @Param("newPs") String newPs);
}
