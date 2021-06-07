package com.zhang.recommendation_system.service;

import com.zhang.recommendation_system.pojo.Song;
import com.zhang.recommendation_system.util.result.Result;

public interface ISongService {
    /**
     * 根据id查询歌曲
     * @param iid 歌曲iid
     * @return 单个结果
     */
    Result queryByIid(String iid);

    /**
     * 根据歌曲名查询歌曲
     * @param keyWord 搜索关键词
     * @return 单个结果
     */
    Result queryByKeyword(String keyWord);

    /**
     * * 获得指定条数的音乐信息，根据热度（播放次数）高低排序，范围是：(currentPage-1)*pageSize后的pageSize个数据，实现分页
     * @param currIndex 起始位置，从0开始而不是1，所以currIndex=(currentPage-1)*pageSize
     * @param pageSize 返回的音乐信息个数
     * @return List<Song>类型的音乐信息数组
     */
    Result querySongsHot(int currIndex, int pageSize);

    /**
     * * 获得指定条数的音乐信息，根据发行时间高低排序，范围是：(currentPage-1)*pageSize后的pageSize个数据，实现分页
     * @param currIndex 起始位置，从0开始而不是1，所以currIndex=(currentPage-1)*pageSize
     * @param pageSize 返回的音乐信息个数
     * @return List<Song>类型的音乐信息数组
     */
    Result querySongsNew(int currIndex, int pageSize);

    /**
     * 获得指定条数的音乐信息，但不在获得播放链接，用于管理员管理
     * @param currIndex 起始位置，从0开始而不是1，所以currIndex=(currentPage-1)*pageSize
     * @param pageSize 返回的音乐信息个数
     * @return List<Song>类型的音乐信息数组
     */
    Result querySongsLimit(int currIndex, int pageSize);

    /**
     * 查询所有歌曲
     *
     * @return 歌曲数组
     */
    Result queryAll();

    /**
     * 添加新的歌曲到数据库中
     * @param song 新的歌曲
     * @return
     */
    Result addSong(Song song);

    /**
     * 更新歌曲
     * @param song 更新的歌曲
     * @return
     */
    Result updateSong(Song song);

    /**
     * 根据歌曲id删除歌曲，单个删除
     * @param iid 要删除歌曲的id
     * @return
     */
    Result deleteSongByIid(String iid);

    /**
     * 获得用户的收藏歌曲列表
     * @param uid 用户id
     * @return 用户收藏歌曲列表
     */
    Result queryLoveSongList(String uid);
}
