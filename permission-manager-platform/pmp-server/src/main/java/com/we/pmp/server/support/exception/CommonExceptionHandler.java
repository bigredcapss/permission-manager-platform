package com.we.pmp.server.support.exception;

import com.we.pmp.common.exception.CommonException;
import com.we.pmp.common.response.BaseResponse;
import com.we.pmp.common.response.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 自定义全局的异常处理器
 * @author we
 * @date 2021-05-08 10:17
 **/
@RestControllerAdvice
@Slf4j
public class CommonExceptionHandler {
    /**
     * 无权限异常
     * @param e
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    public BaseResponse handleAuthorizationException(AuthorizationException e){
        log.info("访问了没有经过授权的操作或者资源：",e.fillInStackTrace());
        return new BaseResponse(StatusCode.CurrUserHasNotPermission);
    }

    /**
     * 自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(CommonException.class)
    public BaseResponse handleRRException(CommonException e){
        BaseResponse response=new BaseResponse(e.getCode(),e.getMessage());
        return response;
    }

    /**
     * 数据库出现重复数据记录的异常
     * @param e
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public BaseResponse handleDuplicateKeyException(DuplicateKeyException e){
        BaseResponse response=new BaseResponse(StatusCode.UnknownError);
        log.error(e.getMessage(), e);
        response.setMsg("数据库中已存在该记录!");
        return response;
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse handleException(Exception e){
        BaseResponse response=new BaseResponse(StatusCode.UnknownError);
        log.error(e.getMessage(), e);
        return response;

    }

}
