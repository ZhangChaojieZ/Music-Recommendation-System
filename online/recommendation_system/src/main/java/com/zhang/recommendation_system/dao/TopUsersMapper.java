package com.zhang.recommendation_system.dao;

import com.zhang.recommendation_system.pojo.TopUsers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TopUsersMapper {
    /**
     * 根据用户id查找用户的相似好友
     * @param uid 用户id
     * @return 相似好友id结合，中间使用逗号隔开
     */
    TopUsers queryByUid(@Param("uid") String uid);
}
