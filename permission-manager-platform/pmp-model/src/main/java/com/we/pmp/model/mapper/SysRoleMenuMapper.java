package com.we.pmp.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.we.pmp.model.entity.SysRoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色-菜单关系Mapper
 * @author we
 * @date 2021-05-07 16:55
 **/
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenuEntity> {
    /**
     * 根据角色Id，获取菜单Id列表
     * @param roleId
     * @return
     */
    List<Long> queryMenuIdList(Long roleId);

    /**
     * 根据角色Id列表，批量删除
     * @param roleIds
     * @return
     */
    int deleteBatch(@Param("roleIds") String roleIds);

}
