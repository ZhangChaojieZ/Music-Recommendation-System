package com.zhang.recommendation_system.service.impl;

import com.zhang.recommendation_system.dao.LoveSongMapper;
import com.zhang.recommendation_system.pojo.LoveSong;
import com.zhang.recommendation_system.service.ILoveSongService;
import com.zhang.recommendation_system.util.result.Result;
import com.zhang.recommendation_system.util.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZhangChaojie
 * @Description: TODO(歌曲收藏业务类)
 * @date 2021/5/22 16:43
 */
@Service
public class LoveSongServiceImpl implements ILoveSongService {
    @Autowired
    LoveSongMapper loveSongMapper;

    @Override
    public Result addLoveSong(LoveSong loveSong) {
        // 判空
        if (loveSong == null) {
            return ResultUtil.fail(420, "添加失败，传入数据为空！");
        }
        // 查询该记录是否已经存在，如果存在 则更新收藏记录状态；不存在则添加
        LoveSong res = loveSongMapper.queryByUidAndIid(loveSong);
        if (res == null) {
            // 设置收藏状态和时间戳
            loveSongMapper.addLoveSong(loveSong.setIsDelete("1").setTimestamp(System.currentTimeMillis() + ""));
            return ResultUtil.success(loveSong);
        }
        // 更新收藏记录
        loveSongMapper.updateLoveSong(loveSong.setIsDelete("1").setTimestamp(System.currentTimeMillis() + ""));
        return ResultUtil.success(loveSong);
    }

    @Override
    public Result cancelLoveSong(LoveSong loveSong) {
        // 判空
        if (loveSong == null) {
            return ResultUtil.fail(420, "添加失败，传入数据为空！");
        }
        // 如果该记录存在则更新，否则提示错误
        // LoveSong res = loveSongMapper.queryByUidAndIid(loveSong);
        // 更新收藏记录
        loveSongMapper.updateLoveSong(loveSong.setIsDelete("0").setTimestamp(System.currentTimeMillis() + ""));
        return ResultUtil.success(loveSong);
    }
}
