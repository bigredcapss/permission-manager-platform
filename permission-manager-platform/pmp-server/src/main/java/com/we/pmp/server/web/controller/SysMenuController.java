package com.we.pmp.server.web.controller;

import com.google.common.collect.Maps;
import com.we.pmp.common.response.BaseResponse;
import com.we.pmp.common.response.StatusCode;
import com.we.pmp.common.utils.Constant;
import com.we.pmp.model.entity.SysMenuEntity;
import com.we.pmp.model.entity.SysUserEntity;
import com.we.pmp.server.support.log.LogAnnotation;
import com.we.pmp.server.web.sercice.ISysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 菜单管理Controller
 * @author we
 * @date 2021-05-08 14:31
 **/
@RestController
@Slf4j
@RequestMapping("/sys/menu")
public class SysMenuController {
    @Autowired
    private ISysMenuService sysMenuService;

    /**
     * 请求菜单列表
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:menu:list")
    public List<SysMenuEntity> list(){
        List<SysMenuEntity> list = new ArrayList<>();
        // 第一种写法~借助mybatis-plus serviceImpl实现
//        list=sysMenuService.list();
//        if (list!=null && !list.isEmpty()){
//            list.stream().forEach(entity -> {
//                if (Constant.MenuType.BUTTON.getValue() == entity.getType() ){
//                    SysMenuEntity menu=sysMenuService.getById(entity.getParentId());
//                    entity.setParentName( (menu!=null && StringUtils.isNotBlank(menu.getName()))? menu.getName() : "");
//                }
//                SysMenuEntity menu=sysMenuService.getById(entity.getParentId());
//                entity.setParentName( (menu!=null && StringUtils.isNotBlank(menu.getName()))? menu.getName() : "");
//            });
//        }
        // 第二种写法~自己写sql
        list = sysMenuService.queryAll();
        return list;
    }


    /**
     * 请求树形层级列表数据
     * @return
     */
    @GetMapping("/select")
    public BaseResponse select(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        Map<String,Object> resMap= Maps.newHashMap();
        try {
            List<SysMenuEntity> list=sysMenuService.queryNotButtonList();
            SysMenuEntity root=new SysMenuEntity();
            root.setMenuId(Constant.TOP_MENU_ID);
            root.setName(Constant.TOP_MENU_NAME);
            root.setParentId(-1L);
            root.setOpen(true);
            list.add(root);
            resMap.put("menuList",list);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        response.setData(resMap);
        return response;
    }

    /**
     * 新增菜单项
     * @param entity
     * @return
     */
    @PostMapping(value = "/save",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:menu:save")
    public BaseResponse save(@RequestBody SysMenuEntity entity){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            log.info("新增菜单~接收到数据：{}",entity);
            String result=this.validateForm(entity);
            if (StringUtils.isNotBlank(result)){
                return new BaseResponse(StatusCode.Fail.getCode(),result);
            }
            sysMenuService.save(entity);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }


    /**
     * 请求菜单详情
     * @param menuId
     * @return
     */
    @GetMapping("/info/{menuId}")
    @RequiresPermissions("sys:menu:info")
    public BaseResponse info(@PathVariable Long menuId){
        if (menuId==null || menuId<=0){
            return new BaseResponse(StatusCode.InvalidParams);
        }

        BaseResponse response=new BaseResponse(StatusCode.Success);
        Map<String,Object> resMap= Maps.newHashMap();
        try {
            resMap.put("menu",sysMenuService.getById(menuId));
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        response.setData(resMap);
        return response;
    }


    /**
     * 修改菜单项
     * @param entity
     * @return
     */
    @LogAnnotation("修改菜单")
    @PostMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:menu:update")
    public BaseResponse update(@RequestBody SysMenuEntity entity){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            log.info("修改菜单~接收到数据：{}",entity);
            String result=this.validateForm(entity);
            if (StringUtils.isNotBlank(result)){
                return new BaseResponse(StatusCode.Fail.getCode(),result);
            }
            sysMenuService.updateById(entity);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    @LogAnnotation("删除菜单")
    @PostMapping(value = "/delete")
    @RequiresPermissions("sys:menu:delete")
    public BaseResponse delete(Long menuId){
        if (menuId==null || menuId<=0 ){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            log.info("删除菜单~接收到数据：{}",menuId);
            SysMenuEntity entity=sysMenuService.getById(menuId);
            if (entity==null){
                return new BaseResponse(StatusCode.InvalidParams);
            }
            List<SysMenuEntity> list=sysMenuService.queryByParentId(entity.getMenuId());
            if (list!=null && !list.isEmpty()){
                return new BaseResponse(StatusCode.MenuHasSubMenuListCanNotDelete);
            }
            sysMenuService.delete(menuId);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }


    /**
     * 验证参数是否正确
     * @param menu
     * @return
     */
    private String validateForm(SysMenuEntity menu) {
        if (StringUtils.isBlank(menu.getName())) {
            return "菜单名称不能为空";
        }
        if (menu.getParentId() == null) {
            return "上级菜单不能为空";
        }
        // 菜单
        if (menu.getType() == Constant.MenuType.MENU.getValue()) {
            if (StringUtils.isBlank(menu.getUrl())) {
                return "菜单链接url不能为空";
            }
        }
        // 上级菜单类型
        int parentType = Constant.MenuType.CATALOG.getValue();
        if (menu.getParentId() != 0) {
            SysMenuEntity parentMenu = sysMenuService.getById(menu.getParentId());
            parentType = parentMenu.getType();
        }
        // 目录、菜单
        if (menu.getType() == Constant.MenuType.CATALOG.getValue() || menu.getType() == Constant.MenuType.MENU.getValue()) {
            if (parentType != Constant.MenuType.CATALOG.getValue()) {
                return "上级菜单只能为目录类型";
            }
            return "";
        }
        // 按钮
        if (menu.getType() == Constant.MenuType.BUTTON.getValue()) {
            if (parentType != Constant.MenuType.MENU.getValue()) {
                return "上级菜单只能为菜单类型";
            }
            return "";
        }
        return "";
    }

    /**
     * 获取首页导航菜单列表
     * @return
     */
    @GetMapping("/nav")
    public BaseResponse nav(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        Map<String,Object> resMap= Maps.newHashMap();
        try {
            SysUserEntity user= (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
            Long userId = user.getUserId();
            List<SysMenuEntity> list=sysMenuService.getUserMenuList(userId);
            resMap.put("menuList",list);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        response.setData(resMap);
        return response;
    }
}
