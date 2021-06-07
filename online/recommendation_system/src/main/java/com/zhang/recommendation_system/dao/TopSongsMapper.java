package com.zhang.recommendation_system.dao;

import com.zhang.recommendation_system.pojo.TopSongs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TopSongsMapper {
    /**
     * 根据音乐iid查找相似音乐
     *
     * @param iid 音乐id
     * @return 相似音乐id合集，中间使用逗号隔开
     */
    TopSongs queryByIid(@Param("iid") String iid);
}
