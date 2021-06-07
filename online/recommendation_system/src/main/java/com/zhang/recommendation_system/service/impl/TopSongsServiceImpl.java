package com.zhang.recommendation_system.service.impl;

import com.zhang.recommendation_system.dao.SongMapper;
import com.zhang.recommendation_system.dao.TopSongsMapper;
import com.zhang.recommendation_system.pojo.Song;
import com.zhang.recommendation_system.service.ITopSongsService;
import com.zhang.recommendation_system.util.httpUtil.SongDetail;
import com.zhang.recommendation_system.util.result.Result;
import com.zhang.recommendation_system.util.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ZhangChaojie
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2021/4/26 23:09
 */
@Service
public class TopSongsServiceImpl implements ITopSongsService {
    @Autowired
    private TopSongsMapper topSongsMapper;
    @Autowired
    private SongMapper songMapper;

    @Override
    public Result querySimilarSongs(String iid) {
        // 相似歌曲列表
        List<Song> songList = new ArrayList<>();
        // 相似歌曲id集合
        List<String> iidList = Arrays.asList(topSongsMapper.queryByIid(iid).getTopSongs().split(","));
        for (String tempId : iidList) {
            songList.add(songMapper.queryByIid(tempId).setDownUrl(SongDetail.getSongMP3Url(tempId)));
        }
        return ResultUtil.success(songList);
    }
}
