package com.we.pmp.server.web.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 页面跳转Controller
 * 注：
 * @RestController类中的所有方法只能返回String、Object、Json等实体对象，不能跳转到模版页面；
 * @Controller类中的方法可以直接通过返回String跳转到jsp、ftl、html等模版页面
 * @author we
 * @date 2021-05-08 14:46
 **/
@Controller
public class SysPageController {
    @GetMapping("modules/{module}/{page}.html")
    public String page(@PathVariable String module, @PathVariable String page){
        return "modules/"+module+"/"+page;
    }

    @GetMapping(value = {"index.html","/"} )
    public String index(){
        return "index";
    }

    @GetMapping("login.html")
    public String login(){
        if (SecurityUtils.getSubject().isAuthenticated()){
            return "redirect:index.html";
        }
        return "login";
    }

    @GetMapping("main.html")
    public String main(){
        return "main";
    }

    @GetMapping("404.html")
    public String notFound(){
        return "404";
    }

}
