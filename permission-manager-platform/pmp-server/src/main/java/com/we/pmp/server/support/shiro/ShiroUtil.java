package com.we.pmp.server.support.shiro;

import com.we.pmp.common.exception.CommonException;
import com.we.pmp.model.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Shiro工具类
 * @author we
 * @date 2021-05-07 21:00
 **/
public class ShiroUtil {
    /**
     * 加密算法
     */
    public final static String hashAlgorithmName = "SHA-256";

    /**
     * 循环次数
     */
    public final static int hashIterations = 16;

    /**
     * 密码加密
     * @param password
     * @param salt
     * @return
     */
    public static String sha256(String password, String salt) {
        return new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toString();
    }

    /**
     * 获取Shiro Session
     * @return
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 获取Shiro Subject
     * @return
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取用户信息
     * @return
     */
    public static SysUserEntity getUserEntity() {
        return (SysUserEntity)SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 获取用户Id
     * @return
     */
    public static Long getUserId() {
        return getUserEntity().getUserId();
    }

    /**
     * 设置Shiro Session
     * @param key
     * @param value
     */
    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    /**
     * 获取Shiro Session
     * @param key
     * @return
     */
    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    /**
     * 判断用户是否登录
     * @return
     */
    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    /**
     * 用户登出
     */
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    /**
     * 获取验证码
     * @param key
     * @return
     */
    public static String getKaptcha(String key) {
        Object object=getSessionAttribute(key);
        if (object==null){
            throw new CommonException("验证码已失效!");
        }
        String newCode=object.toString();
        getSession().removeAttribute(key);
        System.out.println("新的验证码："+newCode);
        return newCode;
    }
}
