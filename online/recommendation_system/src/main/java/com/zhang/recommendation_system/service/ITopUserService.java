package com.zhang.recommendation_system.service;

import com.zhang.recommendation_system.util.result.Result;

public interface ITopUserService {
    /**
     * 根据当前用户id查找相似用户
     * @param uid 当前用户id
     * @return 相似用户集合
     */
    Result querySimilarUsers(String uid);
}
