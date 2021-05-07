package com.we.pmp.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.we.pmp.model.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 用户管理Mapper
 * @author we
 * @date 2021-05-07 16:57
 **/
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserEntity> {
    /**
     * 查询用户的所有权限
     * @param userId
     * @return
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有权限
     * @param userId
     * @return
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户id获取部门Id列表
     * @param userId
     * @return
     */
    Set<Long> queryDeptIdsByUserId(Long userId);

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    SysUserEntity selectByUserName(@Param("userName") String userName);
}
