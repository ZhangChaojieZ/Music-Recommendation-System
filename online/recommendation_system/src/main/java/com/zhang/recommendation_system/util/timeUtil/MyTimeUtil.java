package com.zhang.recommendation_system.util.timeUtil;

import java.text.SimpleDateFormat;

/**
 * @author ZhangChaojie
 * @Description: TODO(和时间处理有关的类)
 * @date 2021/5/9 14:22
 */
public class MyTimeUtil {
    /**
     * 将毫秒数转化为x时x分x秒，形式为xx:xx:xx，这里时歌曲时长，简化为几分几秒xx:xx
     * @param millSeconds 毫秒数
     * @return String类型，
     */
    public static String millSeconds2time(String millSeconds) {
        if ("0".equals(millSeconds)) {
            return "00:00";
        }
        // 将字符串形式的毫秒数转化为时间
        int num = Integer.parseInt(millSeconds);
        int seconds = num / 1000;
        int minute = seconds /60;
        int second = seconds - minute * 60;
        if (second < 10) {
            return minute + ":0" + second;
        }else {
            return minute + ":" + second;
        }

    }

    /**
     * 时间格式化，将毫秒数转化为格式化的时间：YY-MM-DD HH-MM-SS
     * @param millSeconds 毫秒数
     * @return 格式化之后的字符串
     */
    public static String timeFormat(String millSeconds) {
        if ("0".equals(millSeconds)) {
            return "2021-01-01 00:00:00";
        }else {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Double.parseDouble(millSeconds));
        }
    }
}
