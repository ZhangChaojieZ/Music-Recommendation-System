package com.zhang.recommendation_system.dao;

import com.zhang.recommendation_system.pojo.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecordMapper {
    /**
     * 根据用户id和歌曲id查找对应的播放记录信息
     * @param uid 用户id
     * @param iid 歌曲id
     * @return 对应的一条记录
     */
    Record queryByUidAndIid(@Param("uid") String uid, @Param("iid") String iid);

    /**
     * 根据用户id查找该用户的所有播放记录
     * @param uid 用户id
     * @return 用户播放记录集合，List<Record>类型
     */
    List<Record> queryByUid(@Param("uid") String uid);

    /**
     * 添加播放记录
     * @param record 要添加的播放记录
     */
    void addRecord(Record record);

    /**
     * 更新播放记录
     * @param record 新的播放记录
     */
    void updateRecordByUidAndIid(Record record);
}
