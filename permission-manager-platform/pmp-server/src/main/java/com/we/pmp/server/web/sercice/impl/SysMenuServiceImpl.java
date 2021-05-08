package com.we.pmp.server.web.sercice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.we.pmp.common.utils.Constant;
import com.we.pmp.model.entity.SysMenuEntity;
import com.we.pmp.model.entity.SysRoleMenuEntity;
import com.we.pmp.model.mapper.SysMenuMapper;
import com.we.pmp.model.mapper.SysUserMapper;
import com.we.pmp.server.web.sercice.ISysMenuService;
import com.we.pmp.server.web.sercice.ISysRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜单管理接口实现
 * @author we
 * @date 2021-05-08 09:35
 **/
@Service
@Slf4j
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuEntity> implements ISysMenuService {
    @Autowired
    private ISysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 获取所有菜单列表
     * @return
     */
    @Override
    public List<SysMenuEntity> queryAll() {
        return baseMapper.queryList();
    }

    /**
     * 获取不包含菜单的树形层级列表数据
     * @return
     */
    @Override
    public List<SysMenuEntity> queryNotButtonList() {
        return baseMapper.queryNotButtonList();
    }

    /**
     * 根据父级菜单id查询其下的子菜单列表
     * @param menuId
     * @return
     */
    @Override
    public List<SysMenuEntity> queryByParentId(Long menuId) {
        return baseMapper.queryListParentId(menuId);
    }

    /**
     * 删除菜单
     * @param menuId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long menuId) {
        removeById(menuId);
        sysRoleMenuService.remove(new QueryWrapper<SysRoleMenuEntity>().eq("menu_id",menuId));
    }

    /**
     * 获取首页左边导航菜单栏
     * @param currUserId
     * @return
     */
    @Override
    public List<SysMenuEntity> getUserMenuList(Long currUserId) {
        List<SysMenuEntity> list= Lists.newLinkedList();
        if (currUserId.equals(Constant.SUPER_ADMIN)){
            list=getAllMenuList(null);
        }else{
            // 非超级管理员的情况 ~ 根据分配给用户角色~菜单的关联信息来获取
            List<Long> menuIdList=sysUserMapper.queryAllMenuId(currUserId);
            list=getAllMenuList(menuIdList);
        }
        return list;
    }

    /**
     * 获取所有菜单列表
     * @param menuIdList
     * @return
     */
    private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList){
        List<SysMenuEntity> menuList=queryListByParentId(0L,menuIdList);
        // 递归获取一级菜单下的子菜单
        getMenuTrees(menuList,menuIdList);
        return menuList;
    }

    /**
     * 根据父id查询子菜单列表 ~ 找出一级菜单列表(找出类型为 目录 的菜单)
     * @param parentId
     * @param menuIdList 针对于非超级管理员的处理 ~ 获取分配的角色下的菜单id列表
     * @return
     */
    private List<SysMenuEntity> queryListByParentId(Long parentId,List<Long> menuIdList){
        List<SysMenuEntity> menuList=baseMapper.queryListParentId(parentId);
        if (menuIdList==null || menuIdList.isEmpty()){
            return menuList;
        }
        //在 所有的一级菜单列表 中 找出存在于 “用户分配的菜单列表中”
        List<SysMenuEntity> userMenuList=Lists.newLinkedList();
        for (SysMenuEntity entity:menuList){
            if (menuIdList.contains(entity.getMenuId())){
                userMenuList.add(entity);
            }
        }
        return userMenuList;
    }


    /**
     * 递归思想：何时开始；何时结束；做什么事情
     * @param menuList
     * @param menuIdList
     * @return
     */
    private List<SysMenuEntity> getMenuTrees(List<SysMenuEntity> menuList,List<Long> menuIdList){
        List<SysMenuEntity> subMenuList= Lists.newLinkedList();

        List<SysMenuEntity> tempList;
        for (SysMenuEntity entity:menuList){
            // 当前菜单类型为 目录（一级菜单），即进行遍历； （递归终止：不是目录；或者子菜单列表为空）
            if (entity.getType() == Constant.MenuType.CATALOG.getValue()){
                tempList=queryListByParentId(entity.getMenuId(),menuIdList);
                entity.setList(getMenuTrees(tempList,menuIdList));
            }
            subMenuList.add(entity);
        }
        return subMenuList;
    }

}
