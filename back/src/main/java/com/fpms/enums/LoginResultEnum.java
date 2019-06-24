package com.fpms.enums;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/18 18:50
 * @description: 登录结果枚举类
 * @modified :
 */
public enum LoginResultEnum {
    /*输入信息为空*/
    INPUT_NULL(-101,"输入信息为空"),
    USER_NOT_EXIT(-102,"用户未注册"),
    NOT_MATCH(-103,"用户名和密码不匹配"),
    SUCCESS(1,"登录成功");

    LoginResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;

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
}
