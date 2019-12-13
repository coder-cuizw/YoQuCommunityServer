package net.gupt.community.exception;

import lombok.extern.slf4j.Slf4j;
import net.gupt.community.entity.CodeMsg;
import net.gupt.community.entity.Result;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
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
@RestControllerAdvice(annotations = {RestController.class, Controller.class})
public class SpringExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        e.printStackTrace();
        if (e instanceof GlobalException) {
            GlobalException globalException = (GlobalException) e;
            return Result.error(globalException.getCode(), globalException.getMessage());
        } else {
            return Result.error(CodeMsg.SYSTEM_ERROR);
        }
    }

    /**
     * List索引越界异常
     *
     * @param e 异常信息
     * @return Result
     */
    @ExceptionHandler(value = IndexOutOfBoundsException.class)
    @ResponseBody
    public Result indexOut(Exception e) {
        if (e instanceof IndexOutOfBoundsException) {
            return Result.error(CodeMsg.MISSING_RECORD);
        } else {
            GlobalException globalException = (GlobalException) e;
            return Result.error(globalException.getCode(), globalException.getMessage());
        }
    }

    /**
     * 重复绑定异常
     *
     * @param e 异常信息
     * @return Result
     */
    @ExceptionHandler(value = DuplicateKeyException.class)
    @ResponseBody
    public Result duplicateKey(Exception e) {
        final String exceptionInfo = "tbl_student";
        if (e instanceof DuplicateKeyException && e.getCause().toString().contains(exceptionInfo)) {
            return Result.error(CodeMsg.REPEAT_BINDING);
        } else if (e instanceof DuplicateKeyException) {
            return Result.error(CodeMsg.UNIQUE_INDEX);
        } else {
            return Result.error(CodeMsg.SYSTEM_ERROR);
        }
    }

    /**
     * 请求参数丢失异常
     *
     * @param e 异常对象
     * @return Result
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public Result missingServletRequestParameter(Exception e) {
        if (e instanceof MissingServletRequestParameterException) {
            return Result.error(CodeMsg.MISSING_PARAMETER);
        } else {
            GlobalException globalException = (GlobalException) e;
            return Result.error(globalException.getCode(), globalException.getMessage());
        }
    }

    /**
     * 非法请求异常
     *
     * @param e 异常信息
     * @return Result
     */
    @ExceptionHandler(value = IllegalRequestException.class)
    @ResponseBody
    public Result illegalRequest(Exception e) {
        switch (e.getMessage()) {
            case "游客无权限访问":
                return Result.error(CodeMsg.VISITOR_FORBIDDEN);
            case "请求频繁":
                return Result.error(CodeMsg.REQUEST_FREQUENT);
            default:
                return Result.error(CodeMsg.SYSTEM_ERROR);
        }
    }

    /**
     * 数据完整性异常
     *
     * @param e 异常
     * @return result
     */
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    @ResponseBody
    public Result dataIntegrityViolation(Exception e) {
        if (e instanceof DataIntegrityViolationException) {
            return Result.error(CodeMsg.DATA_EXCEPTION);
        } else {
            GlobalException globalException = (GlobalException) e;
            return Result.error(globalException.getCode(), globalException.getMessage());
        }
    }
}
