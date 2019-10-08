package net.gupt.community.entity;

/**
 * <h3>gupt-community</h3>
 * <p>请求失败状态码对应的</p>
 *
 * @author : Cui
 * @date : 2019-08-03 15:28
 **/
public enum CodeMsg {


    /**
     * Description 响应状态码 <br/>
     *
     * @date 2019/10/8 17:10<br/>
     */
    SUCCESS(0, "请求成功"),
    FAILED(1, "请求失败"),
    REQUEST_ILLEGAL(2, "请求不合法"),
    LOGIN_SUCCESS(10001, "登陆成功"),
    LOGIN_FAILED(10002, "登陆失败"),
    BINDING_SUCCESS(20001, "绑定成功"),
    BINDING_FAILED(20002, "绑定失败"),
    REPORT_SUCCESS(30001, "举报成功"),
    REPORT_FAILED(30002, "举报失败"),
    RECOMMEND_SUCCESS(30001, "反馈成功"),
    RECOMMEND_FAILED(30002, "反馈失败"),
    POST_SUCCESS(40001, "发送成功"),
    POST_FAILED(40002, "发送失败"),
    SYSTEM_ERROR(50001, "系统异常"),
    UPDATE_SUCCESS(60001, "更新成功"),
    UPDATE_FAILED(60002, "更新失败"),
    REQUEST_FREQUENT(50003, "请求过于频繁"),
    DELETE_SUCCESS(60003, "删除成功"),
    LOST_RECORD(40004, "记录不存在"),
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
