package net.gupt.community.entity;

import lombok.Data;

/**
 * <h3>gupt-community</h3>
 * <p>请求失败状态码对应的</p>
 *
 * @author : Cui
 * @date : 2019-08-03 15:28
 **/
public enum CodeMsg {

    SUCCESS(0, "请求成功"),
    FAILED(1, "请求失败"),
    REQUEST_ILLEGAL(2, "请求不合法"),
    LOGIN_SUCCESS(10001, "登陆成功"),
    LOGIN_FAILED(10002, "登陆失败"),
    BINDING_SUCCESS(20001, "绑定成功"),
    BINDING_FAILED(20002, "绑定失败"),
    REPORT_SUCCESS(30001, "举报成功"),
    REPORT_FAILED(30002, "举报失败"),
    POST_SUCCESS(40001, "发送成功"),
    POST_FAILED(40002, "发送失败"),
    SYSTEM_ERROR(50001, "系统异常"),
    UPDATE_SUCCESS(60001, "更新成功"),
    UPDATE_FAILED(60002, "更新失败"),
    DELETE_SUCCESS(60003, "删除成功"),
    DELETE_FAILED(60004, "删除失败");

    private int code;
    private String msg;

    CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
