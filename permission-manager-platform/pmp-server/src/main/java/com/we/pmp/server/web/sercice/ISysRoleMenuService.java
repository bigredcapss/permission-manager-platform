package com.we.pmp.server.web.sercice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.we.pmp.model.entity.SysRoleMenuEntity;

import java.util.List;

/**
 * 角色-菜单关系Service
 * @author we
 * @date 2021-05-08 09:21
 **/
public interface ISysRoleMenuService extends IService<SysRoleMenuEntity> {

    /**
     * 维护角色-菜单关联信息
     * @param roleId
     * @param menuIdList
     */
    void saveOrUpdate(Long roleId, List<Long> menuIdList);

    /**
     * 根据角色id批量删除
     * @param roleIds
     */
    void deleteBatch(List<Long> roleIds);

    /**
     * 获取角色对应的菜单列表
     * @param roleId
     * @return
     */
    List<Long> queryMenuIdList(Long roleId);
}
