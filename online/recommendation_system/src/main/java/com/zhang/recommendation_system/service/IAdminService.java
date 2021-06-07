package com.zhang.recommendation_system.service;

import com.zhang.recommendation_system.pojo.Admin;
import com.zhang.recommendation_system.util.result.Result;

public interface IAdminService {
    /**
     * 管理员登录业务接口
     * @param admin 传入一个Admin类型的对象，判断是否在数据库中
     * @return Result类型的结果
     */
    Result adminLogin(Admin admin);
}
