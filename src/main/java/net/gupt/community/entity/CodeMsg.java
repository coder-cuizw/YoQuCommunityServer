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
    SUCCESS(200, "请求成功"),
    FAILED(201, "请求失败"),
    BINDING_NOT(203, "该用户未绑定邮院社区，请先绑定"),
    TOKEN_EXPIRED(400, "token已过期"),
    TOKEN_NONEMPTY(401, "token不能为空"),
    REQUEST_ILLEGAL(202, "请求不合法"),
    LOGIN_FAILED(10002, "登陆失败"),
    BINDING_FAILED(20002, "绑定失败"),
    REPEAT_BINDING(20003, "重复绑定"),
    UNIQUE_INDEX(20004, "重复数据"),
    REPORT_FAILED(30002, "举报失败"),
    RECOMMEND_FAILED(30002, "反馈失败"),
    POST_FAILED(40002,  "发送失败"),
    MISSING_PARAMETER(40003, "参数异常"),
    MISSING_RECORD(40004, "记录不存在"),
    SYSTEM_ERROR(50001, "系统异常"),
    UPDATE_FAILED(60002, "更新失败"),
    REQUEST_FREQUENT(50003, "请求过于频繁"),
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
