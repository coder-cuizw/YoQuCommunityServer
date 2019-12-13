package net.gupt.community.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * ClassName  IllegalRequestException <br/>
 * Description 非法请求异常 <br/>
 *
 * @author YG
 * @version 1.0
 * @date 2019/11/19 18:12<br/>
 * @since JDK 1.8
 */
@Getter
@Setter
public class IllegalRequestException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;

    public IllegalRequestException(String message) {
        super(message);
    }

    public IllegalRequestException() {
        super();
    }

    public IllegalRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalRequestException(Throwable cause) {
        super(cause);
    }

    protected IllegalRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
