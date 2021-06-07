package com.zhang.recommendation_system.dao;

import com.zhang.recommendation_system.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminMapper {
    /**
     * 根据管理员账号查找管理员
     * @param name 管理员账号
     * @return 如果数据库中存在该管理员则返回管理员对象，否则返回空值
     */
    Admin queryByName(@Param("name") String name);
}
