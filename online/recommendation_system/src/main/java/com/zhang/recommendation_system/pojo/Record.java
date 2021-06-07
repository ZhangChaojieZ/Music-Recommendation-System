package com.zhang.recommendation_system.pojo;

import org.springframework.stereotype.Component;

/**
 * @author ZhangChaojie
 * @Description: TODO(播放记录实体类)
 * @date 2021/4/25 20:18
 */
@Component
public class Record {
    // 歌曲id
    private String iid;
    // 用户id
    private String uid;
    // 歌曲名称
    private String name;
    // 歌手名称
    private String songName;
    // 播放次数
    private String weight;
    // 最近依次播放时间
    private String timestamp;

    public Record() {
    }

    public String getIid() {
        return iid;
    }

    public Record setIid(String iid) {
        this.iid = iid;
        return this;
    }

    public String getUid() {
        return uid;
    }

    public Record setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getName() {
        return name;
    }

    public Record setName(String name) {
        this.name = name;
        return this;
    }

    public String getSongName() {
        return songName;
    }

    public Record setSongName(String songName) {
        this.songName = songName;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public Record setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Record setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @Override
    public String toString() {
        return "Record{" +
                "iid='" + iid + '\'' +
                ", uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", songName='" + songName + '\'' +
                ", weight='" + weight + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
