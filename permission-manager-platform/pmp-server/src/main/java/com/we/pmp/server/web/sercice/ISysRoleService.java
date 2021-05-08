package com.we.pmp.server.web.sercice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.we.pmp.common.response.PageResult;
import com.we.pmp.model.entity.SysRoleEntity;

import java.util.Map;

/**
 * 角色管理Service
 * @author we
 * @date 2021-05-08 09:23
 **/
public interface ISysRoleService extends IService<SysRoleEntity> {
    /**
     * 分页列表模糊查询
     * @param map
     * @return
     */
    PageResult queryPage(Map<String,Object> map);

    /**
     * 新增角色信息
     * @param role
     */
    void saveRole(SysRoleEntity role);

    /**
     * 更新角色信息
     * @param role
     */
    void updateRole(SysRoleEntity role);

    /**
     * 批量删除角色信息
     * @param ids
     */
    void deleteBatch(Long[] ids);
}
