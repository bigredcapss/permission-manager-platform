package com.we.pmp.server.web.controller;

import com.google.common.collect.Maps;
import com.we.pmp.common.response.BaseResponse;
import com.we.pmp.common.response.PageResult;
import com.we.pmp.common.response.StatusCode;
import com.we.pmp.common.utils.ValidatorUtil;
import com.we.pmp.model.entity.SysPostEntity;
import com.we.pmp.server.support.log.LogAnnotation;
import com.we.pmp.server.web.sercice.ISysPostService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 岗位管理Controller
 * @author we
 * @date 2021-05-08 14:49
 **/
@RestController
@Slf4j
@RequestMapping("/sys/post")
public class SysPostController {
    @Autowired
    private ISysPostService sysPostService;

    /**
     * 分页列表模糊查询
     * @param paramMap
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:post:list")
    public BaseResponse list(@RequestParam Map<String,Object> paramMap){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        Map<String,Object> resMap= Maps.newHashMap();
        try {
            PageResult page=sysPostService.queryPage(paramMap);
            resMap.put("page",page);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        response.setData(resMap);
        return response;
    }

    /**
     * 新增岗位
     * @param entity
     * @param result
     * @return
     */
    @LogAnnotation("新增岗位")
    @PostMapping(value = "/save",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:post:save")
    public BaseResponse save(@RequestBody @Validated SysPostEntity entity, BindingResult result){
        String res= ValidatorUtil.checkResult(result);
        if (StringUtils.isNotBlank(res)){
            return new BaseResponse(StatusCode.InvalidParams.getCode(),res);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            log.info("新增岗位~接收到数据：{}",entity);
            sysPostService.savePost(entity);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }


    /**
     * 岗位详情
     * @param id
     * @return
     */
    @GetMapping(value = "/info/{id}")
    @RequiresPermissions("sys:post:info")
    public BaseResponse info(@PathVariable Long id){
        if (id==null || id<=0){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        Map<String,Object> resMap=Maps.newHashMap();
        try {
            log.info("岗位详情~接收到数据：{}",id);
            resMap.put("post",sysPostService.getById(id));
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        response.setData(resMap);
        return response;
    }

    /**
     * 更新岗位信息
     * @param entity
     * @param result
     * @return
     */
    @LogAnnotation("修改岗位")
    @PostMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:post:update")
    public BaseResponse update(@RequestBody @Validated SysPostEntity entity, BindingResult result){
        String res= ValidatorUtil.checkResult(result);
        if (StringUtils.isNotBlank(res)){
            return new BaseResponse(StatusCode.InvalidParams.getCode(),res);
        }
        if (entity.getPostId()==null || entity.getPostId()<=0){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            log.info("修改岗位~接收到数据：{}",entity);
            sysPostService.updatePost(entity);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }


    /**
     * 删除岗位信息
     * @param ids
     * @return
     */
    @LogAnnotation("删除岗位")
    @PostMapping(value = "/delete",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:post:delete")
    public BaseResponse delete(@RequestBody Long[] ids){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            log.info("删除岗位~接收到数据：{}", Arrays.asList(ids));
            sysPostService.deletePatch(ids);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    /**
     * 岗位列表
     * @return
     */
    @GetMapping("/select")
    @ResponseBody
    public BaseResponse select(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            log.info("岗位列表~select..");
            Map<String,Object> resMap= Maps.newHashMap();
            resMap.put("list",sysPostService.list());
            response.setData(resMap);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }
}
