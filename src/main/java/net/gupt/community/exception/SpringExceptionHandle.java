package net.gupt.community.exception;

import lombok.extern.slf4j.Slf4j;
import net.gupt.community.entity.CodeMsg;
import net.gupt.community.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <h3>gupt-community</h3>
 * <p>全局异常处理类</p>
 *
 * @author : Cui
 * @date : 2019-08-03 16:40
 **/
@Slf4j
@RestControllerAdvice(annotations={RestController.class, Controller.class})
public class SpringExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof GlobalException) {
            GlobalException globalException = (GlobalException) e;
            return Result.error(globalException.getCode(), globalException.getMessage());
        }else {
            log.error("系统异常", e);
            return Result.error(CodeMsg.SYSTEM_ERROR);
        }
    }

}
