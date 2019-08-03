package net.gupt.community.exception;

import net.gupt.community.entity.CodeMsg;

/**
 * <h3>gupt-community</h3>
 * <p>统一异常</p>
 *
 * @author : Cui
 * @date : 2019-08-03 16:29
 **/
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected int code;

    protected String msg;

    protected String message;

    public GlobalException(CodeMsg enums, String message) {
        super();
        this.code = enums.getCode();
        this.msg = enums.getMsg();
        this.message = message;
    }

    public GlobalException(CodeMsg enums) {
        super();
        this.code = enums.getCode();
        this.msg = enums.getMsg();
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

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GlobalException() {
        super();
    }

    public GlobalException(String message, Throwable cause) {
        super(message, cause);
    }

    public GlobalException(String message) {
        super(message);
    }

}
