package com.we.pmp.server.web.controller;

import com.google.common.collect.Maps;
import com.we.pmp.common.response.BaseResponse;
import com.we.pmp.common.response.PageResult;
import com.we.pmp.common.response.StatusCode;
import com.we.pmp.common.utils.ValidatorUtil;
import com.we.pmp.model.entity.SysDictEntity;
import com.we.pmp.server.web.sercice.ISysDictService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 数据字典Controller
 * @author we
 * @date 2021-05-08 11:52
 **/
@RestController
@Slf4j
@RequestMapping("/sys/dict")
public class SysDictController {
    @Autowired
    private ISysDictService sysDictService;

    /**
     * 分页查询数据字典
     * @param params
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:dict:list")
    public BaseResponse list(@RequestParam Map<String, Object> params){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            PageResult page = sysDictService.queryPage(params);
            Map<String,Object> resMap= Maps.newHashMap();
            resMap.put("page", page);
            response.setData(resMap);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }


    /**
     * 获取数据字典详情
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:dict:info")
    public BaseResponse info(@PathVariable Long id){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        Map<String,Object> resMap=Maps.newHashMap();
        try {
            SysDictEntity entity = sysDictService.getById(id);
            resMap.put("dict", entity);
            response.setData(resMap);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.UpdatePasswordFail);
        }
        return response;
    }

    /**
     * 新增数据字典
     * @param dict
     * @param result
     * @return
     */
    @PostMapping("/save")
    @RequiresPermissions("sys:dict:save")
    public BaseResponse save(@RequestBody @Validated SysDictEntity dict, BindingResult result){
        String res= ValidatorUtil.checkResult(result);
        if (StringUtils.isNotBlank(res)){
            return new BaseResponse(StatusCode.Fail.getCode(),res);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            sysDictService.save(dict);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    /**
     * 更新数据字典
     * @param dict
     * @param result
     * @return
     */
    @PostMapping("/update")
    @RequiresPermissions("sys:dict:update")
    public BaseResponse update(@RequestBody @Validated SysDictEntity dict, BindingResult result){
        String res= ValidatorUtil.checkResult(result);
        if (StringUtils.isNotBlank(res)){
            return new BaseResponse(StatusCode.Fail.getCode(),res);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            sysDictService.updateById(dict);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    /**
     * 删除数据字典
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    @RequiresPermissions("sys:dict:delete")
    public BaseResponse delete(@RequestBody Long[] ids){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            sysDictService.removeByIds(Arrays.asList(ids));
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

}
