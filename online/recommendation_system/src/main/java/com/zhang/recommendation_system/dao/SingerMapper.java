package com.zhang.recommendation_system.dao;

import com.zhang.recommendation_system.pojo.Singer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SingerMapper {
    /**
     * 根据歌手id查询歌手信息
     * @param name 歌手姓名
     * @return 歌手实例，Singer类型
     */
    String queryByName(@Param("name") String name);
}
