package com.we.pmp.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.we.pmp.model.entity.SysUserPostEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 用户岗位关系Mapper
 * @author we
 * @date 2021-05-07 16:58
 **/
@Mapper
public interface SysUserPostMapper extends BaseMapper<SysUserPostEntity> {
    /**
     * 根据用户Id 获取角色Id列表
     * @param userId
     * @return
     */
    List<Long> queryPostIdList(Long userId);

    /**
     * 根据角色Id列表，批量删除
     * @param roleIds
     * @return
     */
    int deleteBatch(@Param("roleIds") String roleIds);

    /**
     * 根据用户id获取用户-岗位详情
     * @param userId
     * @return
     */
    List<SysUserPostEntity> getByUserId(@Param("userId") Long userId);

    /**
     * 根据用户id获取岗位列表
     * @param userId
     * @return
     */
    Set<String> getPostNamesByUserId(@Param("userId") Long userId);

}
