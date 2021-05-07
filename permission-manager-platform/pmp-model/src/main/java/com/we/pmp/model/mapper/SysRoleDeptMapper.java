package com.we.pmp.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.we.pmp.model.entity.SysRoleDeptEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色-部门关系Mapper
 * @author we
 * @date 2021-05-07 16:55
 **/
@Mapper
public interface SysRoleDeptMapper extends BaseMapper<SysRoleDeptEntity> {
    /**
     * 根据角色Id数组，获取部门Id列表
     * @param roleIds
     * @return
     */
    List<Long> queryDeptIdList(Long[] roleIds);

    /**
     * 根据角色Id数组，批量删除
     * @param roleIds
     * @return
     */
    int deleteBatch(@Param("roleIds") String roleIds);

    /**
     * 根据角色Id获取部门Id列表
     * @param roleId
     * @return
     */
    List<Long> queryDeptIdListByRoleId(@Param("roleId") Long roleId);
}
