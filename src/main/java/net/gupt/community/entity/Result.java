package net.gupt.community.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

/**
 * <h3>GuptCommunity</h3>
 * <p>请求返回通用类</p>
 *
 * @author : Cui
 * @date : 2019-07-22 14:59
 **/
@Data
@ApiModel(description = "响应对象")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1963308473592728309L;

    public static final int SUCCESS_CODE = 200;
    public static final String SUCCESS_MESSAGE = "请求成功";
    /**
     * 请求返回码？0失败1成功
     */
    @ApiModelProperty(value = "响应码", name = "code", required = true, example = SUCCESS_CODE + "")
    private int code;
    /**
     * 返回信息
     */
    @ApiModelProperty(value = "响应消息", name = "msg", required = true, example = SUCCESS_MESSAGE)
    private String msg;

    @ApiModelProperty(value = "响应数据", name = "data")
    private T data;

    public Result() {

    }

    private Result(T data) {
        this.data = data;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result(CodeMsg codeMsg) {
        if (codeMsg != null) {
            this.code = codeMsg.getCode();
            this.msg = codeMsg.getMsg();
        }
    }

    private Result(CodeMsg codeMsg, T data) {
        if (codeMsg != null) {
            this.code = codeMsg.getCode();
            this.msg = codeMsg.getMsg();
        }
        if (data != null) {
            this.data = data;
        }
    }


    /**
     * 成功时候的调用
     */
    public static <T> Result<T> success(CodeMsg codeMsg) {
        return new Result<>(codeMsg);
    }

    /**
     * 成功时候的调用
     */
    public static <T> Result<T> success(CodeMsg codeMsg, T data) {
        return new Result<>(codeMsg, data);
    }


    /**
     * 失败时候的调用
     */
    public static <T> Result<T> error(CodeMsg codeMsg) {
        return new Result<>(codeMsg);
    }

    /**
     * 失败时候的调用
     */
    public static <T> Result<T> error(int code, String msg) {
        return new Result<>(code, msg);
    }

    public static <T> Result<T> error(CodeMsg codeMsg, T data) {
        return new Result<>(codeMsg, data);
    }
}
