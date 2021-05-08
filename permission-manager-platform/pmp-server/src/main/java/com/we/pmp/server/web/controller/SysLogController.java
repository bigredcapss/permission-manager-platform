package com.we.pmp.server.web.controller;

import com.google.common.collect.Maps;
import com.we.pmp.common.response.BaseResponse;
import com.we.pmp.common.response.PageResult;
import com.we.pmp.common.response.StatusCode;
import com.we.pmp.server.web.sercice.ISysLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 系统日志Controller
 * @author we
 * @date 2021-05-08 11:57
 **/
@RestController
@Slf4j
@RequestMapping("/sys/log")
public class SysLogController {
    @Autowired
    private ISysLogService sysLogService;

    /**
     * 日志查询
     * @param params
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public BaseResponse list(@RequestParam Map<String, Object> params){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        Map<String,Object> resMap= Maps.newHashMap();
        try {
            log.info("日志模块-列表查询");
            PageResult page = sysLogService.queryPage(params);
            resMap.put("page", page);
            response.setData(resMap);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    /**
     * 清除日志记录
     * @return
     */
    @PostMapping("/truncate")
    @ResponseBody
    @RequiresPermissions("sys:log:truncate")
    public BaseResponse truncate(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            sysLogService.truncate();
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }
}
