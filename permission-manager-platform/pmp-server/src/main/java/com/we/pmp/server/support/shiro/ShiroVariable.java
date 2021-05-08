package com.we.pmp.server.support.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

/**
 * 推送给前端使用的Shiro对象变量
 * @author we
 * @date 2021-05-07 21:05
 **/
@Component
public class ShiroVariable {
    /**
     * 判断当前登录用户是否有 指定的权限
     * @param permission
     * @return
     */
    public Boolean hasPermission(String permission){
        Subject subject= SecurityUtils.getSubject();
        return (subject!=null && subject.isPermitted(permission))? true : false;
    }
}
