package com.we.pmp.server.web.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.we.pmp.common.response.BaseResponse;
import com.we.pmp.common.response.StatusCode;
import com.we.pmp.server.support.shiro.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;


/**
 * 登录入口
 * @author we
 * @date 2021-05-08 14:13
 **/
@Controller
@Slf4j
public class SysLoginController {
    @Autowired
    private Producer producer;

    /**
     * 生成验证码方式一
     */
//    @GetMapping("captcha.jpg")
//    public void kaptcha(HttpServletResponse response) throws Exception{
//        byte[] captchaChallengeAsJpeg = null;
//        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
//        try {
//            // 生产验证码字符串并保存到session中
//            String createText = producer.createText();
//            ShiroUtil.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, createText);
//            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
//            BufferedImage challenge = producer.createImage(createText);
//            ImageIO.write(challenge, "jpg", jpegOutputStream);
//        } catch (IllegalArgumentException e) {
//            response.sendError(HttpServletResponse.SC_NOT_FOUND);
//            return;
//        }
//        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
//        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
//        response.setHeader("Cache-Control", "no-store");
//        response.setHeader("Pragma", "no-cache");
//        response.setDateHeader("Expires", 0);
//        response.setContentType("image/jpeg");
//        ServletOutputStream responseOutputStream = response.getOutputStream();
//        responseOutputStream.write(captchaChallengeAsJpeg);
//        responseOutputStream.flush();
//        responseOutputStream.close();
//    }

    /**
     * 生成验证码方式二
     * @param response
     * @throws Exception
     */
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response) throws Exception {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        // 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        BufferedImage image = producer.createImage(text);
        // 保存到shiro session
        ShiroUtil.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        log.info("验证码："+text);
    }

    /**
     * 登录
     * @param username
     * @param password
     * @param captcha
     * @return
     */
    @PostMapping(value = "/sys/login")
    @ResponseBody
    public BaseResponse login(String username, String password, String captcha){
        log.info("用户名：{} 密码：{} 验证码:{}",username,password,captcha);
        // 校验验证码
        String kaptcha=ShiroUtil.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if (!kaptcha.equals(captcha)){
            return new BaseResponse(StatusCode.InvalidCode);
        }
        try {
            // 提交登录
            Subject subject= SecurityUtils.getSubject();
            if (!subject.isAuthenticated()){
                UsernamePasswordToken token=new UsernamePasswordToken(username,password);
                subject.login(token);
            }
        }catch (UnknownAccountException e) {
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }catch (IncorrectCredentialsException e) {
            return new BaseResponse(StatusCode.AccountPasswordNotMatch);
        }catch (LockedAccountException e) {
            return new BaseResponse(StatusCode.AccountHasBeenLocked);
        }catch (AuthenticationException e) {
            return new BaseResponse(StatusCode.AccountValidateFail);
        }
        return new BaseResponse(StatusCode.Success);
    }

    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public String logout(){
        // 销毁当前用户的Shiro Session
        ShiroUtil.logout();
        return "redirect:login.html";
    }


}
