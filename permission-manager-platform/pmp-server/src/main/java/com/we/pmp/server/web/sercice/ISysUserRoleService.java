package com.we.pmp.server.web.sercice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.we.pmp.model.entity.SysUserRoleEntity;

import java.util.List;

/**
 * 用户-角色关系Service
 * @author we
 * @date 2021-05-08 08:58
 **/
public interface ISysUserRoleService extends IService<SysUserRoleEntity> {
    /**
     * 根据角色Id列表批量删除
     * @param roleIds
     */
    void deleteBatch(List<Long> roleIds);

    /**
     * 保存或更新
     * @param userId
     * @param roleIds
     */
    void saveOrUpdate(Long userId, List<Long> roleIds);

    /**
     * 根据用户id查询角色Id列表
     * @param userId
     * @return
     */
    List<Long> queryRoleIdList(Long userId);
}
