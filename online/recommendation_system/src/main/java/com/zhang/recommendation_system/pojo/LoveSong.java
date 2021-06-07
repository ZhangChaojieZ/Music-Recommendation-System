package com.zhang.recommendation_system.pojo;

import org.springframework.stereotype.Component;

/**
 * @author ZhangChaojie
 * @Description: TODO(收藏歌曲记录实体类)
 * @date 2021/4/25 20:18
 */
@Component
public class LoveSong {
    // 歌曲id
    private String iid;
    // 用户id
    private String uid;
    // 歌曲名称
    private String name;
    // 歌手名称
    private String songName;
    // 是否收藏，1表示收藏
    private String isDelete;
    // 最近依次播放时间
    private String timestamp;

    public LoveSong() {
    }

    public String getIid() {
        return iid;
    }

    public LoveSong setIid(String iid) {
        this.iid = iid;
        return this;
    }

    public String getUid() {
        return uid;
    }

    public LoveSong setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getName() {
        return name;
    }

    public LoveSong setName(String name) {
        this.name = name;
        return this;
    }

    public String getSongName() {
        return songName;
    }

    public LoveSong setSongName(String songName) {
        this.songName = songName;
        return this;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public LoveSong setIsDelete(String isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public LoveSong setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @Override
    public String toString() {
        return "LoveSong{" +
                "iid='" + iid + '\'' +
                ", uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", songName='" + songName + '\'' +
                ", isDelete='" + isDelete + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
