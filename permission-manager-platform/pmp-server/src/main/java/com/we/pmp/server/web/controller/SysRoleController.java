package com.we.pmp.server.web.controller;

import com.google.common.collect.Maps;
import com.we.pmp.common.response.BaseResponse;
import com.we.pmp.common.response.PageResult;
import com.we.pmp.common.response.StatusCode;
import com.we.pmp.common.utils.ValidatorUtil;
import com.we.pmp.model.entity.SysRoleEntity;
import com.we.pmp.server.support.log.LogAnnotation;
import com.we.pmp.server.web.sercice.ISysRoleDeptService;
import com.we.pmp.server.web.sercice.ISysRoleMenuService;
import com.we.pmp.server.web.sercice.ISysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 角色Controller
 * @author we
 * @date 2021-05-08 15:04
 **/
@RestController
@Slf4j
@RequestMapping("/sys/role")
public class SysRoleController {
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysRoleMenuService sysRoleMenuService;
    @Autowired
    private ISysRoleDeptService sysRoleDeptService;


    /**
     * 分页列表模糊查询
     * @param paramMap
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:role:list")
    public BaseResponse list(@RequestParam Map<String,Object> paramMap){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            Map<String,Object> resMap= Maps.newHashMap();
            PageResult page=sysRoleService.queryPage(paramMap);
            resMap.put("page",page);
            response.setData(resMap);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    /**
     * 新增角色信息
     * @param entity
     * @param result
     * @return
     */
    @LogAnnotation("新增角色")
    @PostMapping(value = "/save",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:role:save")
    public BaseResponse save(@RequestBody @Validated SysRoleEntity entity, BindingResult result) {
        String res=ValidatorUtil.checkResult(result);
        if (StringUtils.isNotBlank(res)){
            return new BaseResponse(StatusCode.InvalidParams.getCode(),res);
        }
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            log.info("新增角色~接收到数据：{}",entity);
            sysRoleService.saveRole(entity);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

    /**
     * 获取角色详情
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:role:info")
    public BaseResponse info(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return new BaseResponse(StatusCode.InvalidParams);
        }
        BaseResponse response = new BaseResponse(StatusCode.Success);
        Map<String, Object> resMap = Maps.newHashMap();
        try {
            SysRoleEntity role=sysRoleService.getById(id);
            // 获取角色对应的菜单列表
            List<Long> menuIdList=sysRoleMenuService.queryMenuIdList(id);
            role.setMenuIdList(menuIdList);
            // 获取角色对应的部门列表
            List<Long> deptIdList=sysRoleDeptService.queryDeptIdList(id);
            role.setDeptIdList(deptIdList);
            resMap.put("role",role);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(resMap);
        return response;
    }

    /**
     * 修改角色信息
     * @param entity
     * @param result
     * @return
     */
    @LogAnnotation("修改角色")
    @PostMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:role:update")
    public BaseResponse update(@RequestBody @Validated SysRoleEntity entity, BindingResult result) {
        String res= ValidatorUtil.checkResult(result);
        if (StringUtils.isNotBlank(res)){
            return new BaseResponse(StatusCode.InvalidParams.getCode(),res);
        }
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            log.info("修改角色~接收到数据：{}",entity);
            sysRoleService.updateRole(entity);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;

    }


    /**
     * 删除角色信息
     * @param ids
     * @return
     */
    @LogAnnotation("删除角色")
    @PostMapping(value = "/delete",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:role:delete")
    public BaseResponse delete(@RequestBody Long[] ids) {
        if (ids==null || ids.length<=0){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            log.info("删除角色~接收到数据：{}",ids);
            sysRoleService.deleteBatch(ids);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }


    /**
     * 获取角色列表
     * @return
     */
    @GetMapping("/select")
    @ResponseBody
    public BaseResponse select(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            log.info("角色列表~select..");
            Map<String,Object> resMap= Maps.newHashMap();
            resMap.put("list",sysRoleService.list());

            response.setData(resMap);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

}
