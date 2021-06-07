package com.zhang.recommendation_system.util.result;

/**
 * @author ZhangChaojie
 * @Description: TODO(接口返回通用结果类)
 * @date 2021/4/26 16:17
 */
public class Result<T> {
    // 是否成功，成功则值为true
    private Boolean succ;
    // 状态码
    private Integer code;
    // 提示信息
    private String msg;
    // 时间戳
    private String timestamp;
    // 携带的数据
    private T data;

    public Result() {
        super();
    }

    public Result(Boolean succ, Integer code, String msg, String timestamp, T data) {
        this.succ = succ;
        this.code = code;
        this.msg = msg;
        this.timestamp = timestamp;
        this.data = data;
    }

    public Boolean getSucc() {
        return succ;
    }

    public void setSucc(Boolean succ) {
        this.succ = succ;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "succ='" + succ + '\'' +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", data=" + data +
                '}';
    }
}
