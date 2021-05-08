package com.we.pmp.server.web.sercice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.we.pmp.model.entity.SysMenuEntity;

import java.util.List;

/**
 * 菜单管理Service
 * @author we
 * @date 2021-05-08 09:12
 **/
public interface ISysMenuService extends IService<SysMenuEntity> {
    /**
     * 查询所有菜单列表
     * @return
     */
    List<SysMenuEntity> queryAll();

    /**
     * 获取不包含菜单的树形层级列表数据
     * @return
     */
    List<SysMenuEntity> queryNotButtonList();

    /**
     * 根据父级菜单id查询其下的子菜单列表
     * @param menuId
     * @return
     */
    List<SysMenuEntity> queryByParentId(Long menuId);

    /**
     * 删除菜单
     * @param menuId
     */
    void delete(Long menuId);

    /**
     * 获取首页左边导航菜单栏
     * @param currUserId
     * @return
     */
    List<SysMenuEntity> getUserMenuList(Long currUserId);
}
