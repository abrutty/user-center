package com.abruti.usercenter.exception;

import com.abruti.usercenter.common.BaseResponse;
import com.abruti.usercenter.common.ErrorCode;
import com.abruti.usercenter.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * RestControllerAdvice+ExceptionHandler这两个注解的组合，被用作全局异常处理
 * 一旦项目中发生了异常，就会进入使用了RestControllerAdvice注解类中使用了ExceptionHandler注解的方法，
 * 可以在这里处理全局异常，将异常信息输出到指定的位置。
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("businessException: " + e.getMessage(), e);
        return ResultUtils.error(e.getCode(), e.getMessage(), e.getDescription());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("runtimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, e.getMessage(), "");
    }
}
