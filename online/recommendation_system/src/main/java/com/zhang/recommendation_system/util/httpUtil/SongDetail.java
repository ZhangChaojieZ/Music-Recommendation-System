package com.zhang.recommendation_system.util.httpUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * @author ZhangChaojie
 * @Description: TODO(获得歌曲播放url)
 * @date 2021/5/8 14:31
 */
public class SongDetail {
    /**
     * 获得歌曲的播放链接
     * @param id 歌曲id
     * @return
     */
    public static String getSongMP3Url(String id) {
        String url = "http://music.163.com/api/song/enhance/player/url?id=" + id + "&ids=%5B" + id + "%5D&br=3200000";
        Connection.Response response = null;
        try {
            response = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36")
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Accept-Language", "zh-CN,zh;q=0.9")
                    // .header("Content-Length", "408")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    // 当心cookie过期
                    // .header("Cookie", "_iuqxldmzr_=32; _ntes_nnid=75e227d124e6a67d1d01314be3f03b94,1620366968827; _ntes_nuid=75e227d124e6a67d1d01314be3f03b94; NMTID=00OX-RHiK4Gqkn54U-At_5EGuMuc-gAAAF5RWXAzQ; WEVNSM=1.0.0; WM_TID=5tmmvzyzeuZFBEQAEFd%2BkmQyOXbhT4wj; playerid=44944039; WM_NI=Y2MtHELw2NJA%2F6jYFs92DErm7VlO1IThgAr8xDbAC%2Frem2vlMYdvx%2BZnvtgFyBN%2FbmFLG7bHp42Nk1bXr1p9A2Vlmckfto4fZxW%2BfC0GeOskIMftDYtQnjKuyqwBDgjNaDg%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6eeb7d13bae9fafd8f5498aef8ba2d55f938a9bafb63cf2999c8bb16aadf18fb8b72af0fea7c3b92a9bee8395cb4f90a9feafc94bedeaa6bac579fbee8ca6f167f58aa4b0b44398b8a9a2f134bc9caea2c225b2bba3d3f5688f87ac97fb219ab2ba95e64ebb9e82d0e167869489d8e144b4ecfeafea3ba288bed2f33383ee9a8df069a9babc90b76e9aa899cce549f499abb0f2618b93aa95c753b88fa28bca7fbbafb9a3c76daa92afa5e637e2a3; JSESSIONID-WYYY=CD%2BkjenZHc5GuUAccUFGSHCvTIMW0bp8tPnEAShJz1TTNOIMYXEVrWkbKxC%2FwTVhx6TvKlqZi4zANTT6fIbiZSgI%2BuoIiUbPe95rsbk9uhXZWn%5CED76jyp9SYU%2BDNiItx8zruGmXWasSju%2FdnzuQzX8yOUHxEWSzoY9EpR4OPZAVrk30%3A1620455163897")
                    .header("Cookie","NMTID=00ObXPfnA12725gnUSWpw65ghoacRsAAAF5frCynQ")
                    .header("Connection", "keep-alive")
                    .header("Host", "music.163.com")
                    .header("Origin", "https://music.163.com")
                    .header("Referer", "https://music.163.com/")
                    .method(Connection.Method.GET)
                    .timeout(10000)
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println(response);
        if (response != null) {
            // 获得返回的json数据
            JSONObject parse = JSON.parseObject(response.body());
            // if ("200".equals(parse.get("code").toString())) {
            // 抽取data数据数组中的第一项
            String dataStr = parse.getJSONArray("data").get(0).toString();
            // System.out.println("data数据：" + dataStr);
            // 抽取播放链接
            JSONObject data = JSON.parseObject(dataStr);
            // 判断是否可以拿到播放链接，拿到则返回链接，否则返回"None"
            if ("200".equals(data.get("code").toString())) {
                String downUrl = data.get("url").toString();
                System.out.println("歌曲下载url:" + downUrl);
                return downUrl;
            } else {
                return "None";
            }
        } else {
            return "None";
        }

    }
}
