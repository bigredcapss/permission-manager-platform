package com.we.pmp.server.support.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.we.pmp.common.utils.HttpContentUtil;
import com.we.pmp.common.utils.IpUtil;
import com.we.pmp.model.entity.SysLogEntity;
import com.we.pmp.server.support.shiro.ShiroUtil;
import com.we.pmp.server.web.sercice.ISysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 日志切面
 * @author we
 * @date 2021-05-07 20:27
 **/
@Aspect
@Component
public class LogAspect {
    @Autowired
    private ISysLogService sysLogService;

    @Pointcut("@annotation(com.we.pmp.server.support.log.LogAnnotation)")
    public void logPointCut(){
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        long start=System.currentTimeMillis();

        Object result=point.proceed();

        long time=System.currentTimeMillis() - start;

        saveLog(point,time);

        return result;
    }

    /**
     * 保存日志
     * @param point
     * @param time
     */
    private void saveLog(ProceedingJoinPoint point,Long time) throws Exception {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        SysLogEntity logEntity = new SysLogEntity();

        // 获取请求操作的描述信息
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        if(logAnnotation!=null){
            logEntity.setOperation(logAnnotation.value());
        }

        // 获取操作方法名
        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();
        logEntity.setMethod(new StringBuilder(className).append(".").append(methodName).append("()").toString());

        // 获取请求参数
        Object[] args = point.getArgs();
        String params = new ObjectMapper().writeValueAsString(args[0]);
        logEntity.setParams(params);

        // 获取IP地址
        logEntity.setIp(IpUtil.getRemoteIp(HttpContentUtil.getHttpServletRequest()));

        // 其它参数
        logEntity.setCreateDate(DateTime.now().toDate());
        String username = ShiroUtil.getUserEntity().getUsername();
        logEntity.setUsername(username);

        // 执行时间
        logEntity.setTime(time);
        sysLogService.save(logEntity);
    }
}
