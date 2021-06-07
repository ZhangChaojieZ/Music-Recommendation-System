package com.zhang.recommendation_system.service;

import com.zhang.recommendation_system.pojo.Record;
import com.zhang.recommendation_system.util.result.Result;

public interface IRecordService {
    /**
     * 添加播放记录，如果播放记录已经存在，则更新播放记录的播放次数和时间戳
     * @param record 要更新的记录
     * @return
     */
    Result addRecord(Record record);

    /**
     * 根据用户id查找用户的播放记录
     * @param uid 用户id
     * @return 播放记录列表
     */
    Result queryRecordByUid(String uid);
}
