package com.we.pmp.server.web.sercice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.we.pmp.model.entity.SysRoleDeptEntity;

import java.util.List;

/**
 * 角色-部门关系Service
 * @author we
 * @date 2021-05-08 09:19
 **/
public interface ISysRoleDeptService extends IService<SysRoleDeptEntity> {
    /**
     * 维护角色-部门关联信息
     * @param roleId
     * @param deptIdList
     */
    void saveOrUpdate(Long roleId, List<Long> deptIdList);

    /**
     * 根据角色id批量删除
     * @param roleIds
     */
    void deleteBatch(List<Long> roleIds);

    /**
     * 根据角色ID获取部门ID列表
     * @param roleId
     * @return
     */
    List<Long> queryDeptIdList(Long roleId);
}
