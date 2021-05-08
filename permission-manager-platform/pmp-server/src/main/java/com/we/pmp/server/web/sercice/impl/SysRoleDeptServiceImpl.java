package com.we.pmp.server.web.sercice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Joiner;
import com.we.pmp.common.utils.CommonUtil;
import com.we.pmp.model.entity.SysRoleDeptEntity;
import com.we.pmp.model.mapper.SysRoleDeptMapper;
import com.we.pmp.server.web.sercice.ISysRoleDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 角色-部门关系接口实现
 * @author we
 * @date 2021-05-08 09:40
 **/
@Service
@Slf4j
public class SysRoleDeptServiceImpl extends ServiceImpl<SysRoleDeptMapper, SysRoleDeptEntity> implements ISysRoleDeptService {

    /**
     * 维护角色-部门关联信息
     * @param roleId
     * @param deptIdList
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> deptIdList) {
        // 需要先清除旧的关联数据，再插入新的关联信息
        deleteBatch(Arrays.asList(roleId));
        SysRoleDeptEntity entity;
        if (deptIdList!=null && !deptIdList.isEmpty()){
            for (Long dId:deptIdList){
                entity=new SysRoleDeptEntity();
                entity.setRoleId(roleId);
                entity.setDeptId(dId);
                this.save(entity);
            }
        }
    }

    /**
     * 根据角色id批量删除
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
     * 根据角色ID获取部门ID列表
     * @param roleId
     * @return
     */
    @Override
    public List<Long> queryDeptIdList(Long roleId) {
        return baseMapper.queryDeptIdListByRoleId(roleId);
    }
}
