package com.zhang.recommendation_system.service;

import com.zhang.recommendation_system.pojo.User;
import com.zhang.recommendation_system.util.result.Result;
import com.zhang.recommendation_system.util.result.ResultEnum;


public interface IUserService {
    /**
     * 管理员登录业务接口
     * @param user 传入一个user类型的对象，判断是否在数据库中
     * @return Result类型的结果，有状态码、时间戳等信息
     */
    Result userLogin(User user);

    /**
     * 添加用户
     * @param user 待添加的对象
     * @return Result类型的结果，有状态码、时间戳等信息
     */
    Result addUser(User user);

    /**
     * 更新用户信息
     * @param user 要更新的用户对象
     * @return
     */
    Result updateUser(User user);

    /**
     * 更新密码
     * @param uid 用户id
     * @param oldPw 旧密码
     * @param newPw 新密码
     * @return
     */
    Result updatePw(String uid, String oldPw, String newPw);

    /**
     * 获得用户的播放记录
     * @param uid 用户id
     * @return Result类型的结果，有状态码、时间戳等信息
     */
    Result getUserRecord(String uid);

    /**
     * 获得用户的推荐歌曲
     * @param uid 用户id
     * @return
     */
    Result getRecommendSongs(String uid);

    /**
     * 获得用户的相似音乐好友
     * @param uid 用户id
     * @return
     */
    Result getRecommendUsers(String uid);
}
