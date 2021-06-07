package com.zhang.recommendation_system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.zhang.recommendation_system.pojo.Song;
import com.zhang.recommendation_system.service.ISongService;
import com.zhang.recommendation_system.util.result.Result;
import com.zhang.recommendation_system.util.result.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhangChaojie
 * @Description: TODO(用于返回音乐信息)
 * @date 2021/4/26 23:11
 */
@RestController
public class SongController {
    @Autowired
    private ISongService iSongService;

    /**
     * 返回所有歌曲列表
     *
     * @return 所有歌曲列表
     */
    @RequestMapping(value = "/songList", method = RequestMethod.GET)
    public Result songList() {
        return iSongService.queryAll();
    }

    /**
     * 根据歌曲id查找歌曲
     *
     * @return 单首歌曲
     */
    @RequestMapping(value = "/songId", method = RequestMethod.POST)
    public Result songId(@RequestBody String iid) {
        System.out.println(iid);
        JSONObject parse = JSON.parseObject(iid);
        return iSongService.queryByIid(parse.getString("iid"));
    }

    /**
     * 返回热门音乐数据
     *
     * @param currentPage 当前页面
     * @return
     */
    @RequestMapping(value = "/hot", method = RequestMethod.POST)
    public Result songsHot(@RequestBody String currentPage) {
        // 页面大小（偏移量）设置为10
        System.out.println("当前页面：" + currentPage + "页面大小：" + 10);
        // 获得当前页面的整数
        int num = Integer.parseInt(JSON.parseObject(currentPage).get("currentPage").toString());
        int currIndex = (num - 1) * 10;
        return iSongService.querySongsHot(currIndex, 10);
    }

    /**
     * 返回最新音乐数据
     *
     * @param currentPage 当前页面
     * @return
     */
    @RequestMapping(value = "/discover", method = RequestMethod.POST)
    public Result songsNew(@RequestBody String currentPage) {
        // 页面大小（偏移量）设置为10
        System.out.println("当前页面：" + currentPage + "页面大小：" + 10);
        // 获得当前页面的整数
        int num = Integer.parseInt(JSON.parseObject(currentPage).get("currentPage").toString());
        int currIndex = (num - 1) * 10;
        return iSongService.querySongsNew(currIndex, 10);
    }

    /**
     * 返回用户搜索的音乐数据
     *
     * @param keyword 搜索关键词
     * @return
     */
    @RequestMapping(value = "/searchSongs", method = RequestMethod.POST)
    public Result searchSongs(@RequestBody String keyword) {
        // 搜索关键词
        System.out.println("搜索关键词：" + keyword);
        // 获得搜索关键词
        String keywordStr = JSON.parseObject(keyword).get("keyword").toString();
        System.out.println("输入参数：" + keywordStr);
        return iSongService.queryByKeyword(keywordStr);
    }

    /**
     * 添加歌曲
     *
     * @param song 要添加的歌曲
     * @return
     */
    @RequestMapping(value = "/addSong", method = RequestMethod.POST)
    public Result addSong(@RequestBody Song song) {
        System.out.println(song);
        return iSongService.addSong(song);
    }

    /**
     * 更新歌曲
     *
     * @param song 要更新的歌曲
     * @return
     */
    @RequestMapping(value = "/updateSong", method = RequestMethod.POST)
    public Result updateSong(@RequestBody Song song) {
        System.out.println(song);
        return iSongService.updateSong(song);
    }

    /**
     * 删除单首歌曲
     *
     * @param iid 要删除歌曲的id
     * @return
     */
    @RequestMapping(value = "/deleteSong", method = RequestMethod.POST)
    public Result deleteSong(@RequestBody String iid) {
        System.out.println(iid);
        String iid_str = JSON.parseObject(iid).get("iid").toString();
        return iSongService.deleteSongByIid(iid_str);
    }

    /**
     * 返回最新音乐数据
     *
     * @param currentPage 当前页面
     * @return
     */
    @RequestMapping(value = "/display", method = RequestMethod.POST)
    public Result songsNewLimit(@RequestBody String currentPage) {
        // 页面大小（偏移量）设置为10
        System.out.println("当前页面：" + currentPage + "页面大小：" + 10);
        // 获得当前页面的整数
        int num = Integer.parseInt(JSON.parseObject(currentPage).get("currentPage").toString());
        int currIndex = (num - 1) * 10;
        return iSongService.querySongsLimit(currIndex, 10);
    }

    /**
     * 获得用户收藏歌曲列表
     *
     * @param uid 用户id
     * @return 收藏歌曲列表
     */
    @RequestMapping(value = "/loveSongList", method = RequestMethod.POST)
    public Result loveSongList(@RequestBody String uid) {
        System.out.println(uid);
        String uid_str = JSON.parseObject(uid).get("uid").toString();
        return iSongService.queryLoveSongList(uid_str);
    }
}
