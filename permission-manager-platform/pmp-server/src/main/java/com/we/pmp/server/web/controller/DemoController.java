package com.we.pmp.server.web.controller;

import com.we.pmp.common.response.BaseResponse;
import com.we.pmp.common.response.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * demo演示
 * @author we
 * @date 2021-05-08 11:28
 **/
@RestController
@Slf4j
@RequestMapping("/demo")
public class DemoController {

    /**
     * 前后台交互数据格式
     * @param name
     * @return
     */
    @GetMapping("/info")
    @ResponseBody
    public BaseResponse info(String name){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        if(StringUtils.isBlank(name)){
            name = "权限管理平台";
        }
        response.setData(name);
        return response;
    }

    /**
     * 页面传值跳转
     * @param name
     * @param modelMap
     * @return
     */
    @GetMapping("/page")
    public String page(String name, ModelMap modelMap){
        if (StringUtils.isBlank(name)){
            name="权限管理平台!";
        }
        modelMap.put("name",name);
        modelMap.put("app","权限管理系统");
        return "pageOne";
    }





}
