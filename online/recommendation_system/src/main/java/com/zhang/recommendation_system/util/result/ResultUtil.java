package com.zhang.recommendation_system.util.result;

/**
 * @author ZhangChaojie
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2021/4/26 16:28
 */

public class ResultUtil {

    /**
     * 成功且带有数据
     * @param object 传入的对象参数
     * @return Result对象
     */
    public static<T> Result<T> success(Object object) {
        Result result = new Result();
        result.setSucc(true);
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setTimestamp(System.currentTimeMillis()+"");
        result.setData(object);
        return result;
    }

    /**
     * 成功但不带数据
     * @return
     */
    public static<T> Result<T> success() {

        return success(null);
    }

    /**
     * 失败并给出提示信息
     * @param code 失败对应的状态码
     * @param msg 失败提示信息
     * @return 结果提示信息
     */
    public static<T> Result<T> fail(Integer code, String msg) {
        Result result = new Result();
        result.setSucc(false);
        result.setCode(code);
        result.setMsg(msg);
        result.setTimestamp(System.currentTimeMillis()+"");
        return result;
    }
}
