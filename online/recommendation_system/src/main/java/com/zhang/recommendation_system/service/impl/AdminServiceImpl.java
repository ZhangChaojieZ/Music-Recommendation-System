package com.zhang.recommendation_system.service.impl;

import com.zhang.recommendation_system.dao.AdminMapper;
import com.zhang.recommendation_system.pojo.Admin;
import com.zhang.recommendation_system.service.IAdminService;
import com.zhang.recommendation_system.util.result.Result;
import com.zhang.recommendation_system.util.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author ZhangChaojie
 * @Description: TODO()
 * @date 2021/4/26 15:48
 */
@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Result adminLogin(Admin admin) {
        // 查询数据库
        Admin res = adminMapper.queryByName(admin.getName());
        // 这里密码在数据库中实际上应该是加密
        if (res != null && admin.getPassword().equals(res.getPassword())) {
            return ResultUtil.success(admin);
        }else {
            return ResultUtil.fail(400, "管理员不存在");
        }
    }
}
