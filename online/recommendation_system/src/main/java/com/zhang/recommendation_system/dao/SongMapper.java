package com.zhang.recommendation_system.dao;

import com.zhang.recommendation_system.pojo.Song;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SongMapper {

    /**
     * 添加新歌曲
     *
     * @param song 歌曲iid，String类型
     * @return Song实例
     */
    void addSong(Song song);

    /**
     * 根据歌曲id删除歌曲，将删除标志置为0
     *
     * @param iid 歌曲id
     */
    void deleteSong(@Param("iid") String iid);

    /**
     * 更新歌曲
     *
     * @param song 新的歌曲信息
     */
    void updateSong(Song song);

    /**
     * 根据歌曲id查找歌曲信息
     *
     * @param iid 歌曲iid，String类型
     * @return Song实例
     */
    Song queryByIid(@Param("iid") String iid);

    /**
     * 根据歌曲名查询歌曲，模糊查询
     *
     * @param keyWord 搜索关键字
     * @return 查询结果
     */
    List<Song> queryByKeyword(@Param("keyword") String keyWord);

    /**
     * 获得指定条数的音乐信息，根据热度（播放次数）高低排序，范围是：(currentPage-1)*pageSize后的pageSize个数据
     *
     * @param currentIndex 当前页面
     * @param pageSize     页面大小
     * @return pageSize个结果
     */
    List<Song> querySongsHot(@Param("currIndex") int currentIndex, @Param("pageSize") int pageSize);

    /**
     * 获得指定条数的音乐信息，根据发行时间高低排序，范围是：(currentPage-1)*pageSize后的pageSize个数据
     *
     * @param currentIndex 当前页面
     * @param pageSize     页面大小
     * @return pageSize个结果
     */
    List<Song> querySongsNew(@Param("currIndex") int currentIndex, @Param("pageSize") int pageSize);

    /**
     * 查询所有歌曲信息
     *
     * @return 歌曲实例数组，List<Song>类型
     */
    List<Song> queryAll();

}
