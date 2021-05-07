package com.we.pmp.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.we.pmp.model.entity.SysUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色-用户关系Mapper
 * @author we
 * @date 2021-05-07 16:57
 **/
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRoleEntity> {
    /**
     * 根据用户Id 获取角色Id列表
     * @param userId
     * @return
     */
    List<Long> queryRoleIdList(Long userId);

    /**
     * 根据角色Id列表，批量删除
     * @param roleIds
     * @return
     */
    int deleteBatch(@Param("roleIds") String roleIds);
}
