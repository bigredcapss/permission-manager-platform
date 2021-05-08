package com.we.pmp.server.web.sercice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Joiner;
import com.we.pmp.common.utils.CommonUtil;
import com.we.pmp.model.entity.SysUserRoleEntity;
import com.we.pmp.model.mapper.SysUserRoleMapper;
import com.we.pmp.server.web.sercice.ISysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户-角色接口实现
 * @author we
 * @date 2021-05-08 09:46
 **/
@Service
@Slf4j
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRoleEntity> implements ISysUserRoleService {
    /**
     * 批量删除
     * @param roleIds
     */
    @Override
    public void deleteBatch(List<Long> roleIds) {
        if (roleIds!=null && !roleIds.isEmpty()){
            String delIds= Joiner.on(",").join(roleIds);
            baseMapper.deleteBatch(CommonUtil.concatStrToInt(delIds,","));
        }
    }

    /**
     * 维护用户-角色的关联关系
     * @param userId
     * @param roleIds
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long userId, List<Long> roleIds) {
        // 需要先清除旧的关联数据，再插入新的关联信息
        this.remove(new QueryWrapper<SysUserRoleEntity>().eq("user_id",userId));
        if (roleIds!=null && !roleIds.isEmpty()){
            SysUserRoleEntity entity;
            for (Long rId:roleIds){
                entity=new SysUserRoleEntity();
                entity.setRoleId(rId);
                entity.setUserId(userId);
                this.save(entity);
            }
        }
    }

    /**
     * 获取分配给用户的角色id列表
     * @param userId
     * @return
     */
    @Override
    public List<Long> queryRoleIdList(Long userId) {
        return baseMapper.queryRoleIdList(userId);
    }
}
