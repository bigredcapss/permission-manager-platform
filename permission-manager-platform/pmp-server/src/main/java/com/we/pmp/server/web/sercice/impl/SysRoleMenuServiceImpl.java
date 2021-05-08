package com.we.pmp.server.web.sercice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Joiner;
import com.we.pmp.common.utils.CommonUtil;
import com.we.pmp.model.entity.SysRoleMenuEntity;
import com.we.pmp.model.mapper.SysRoleMenuMapper;
import com.we.pmp.server.web.sercice.ISysRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 角色菜单关系接口实现
 * @author we
 * @date 2021-05-08 09:41
 **/
@Service
@Slf4j
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenuEntity> implements ISysRoleMenuService {
    /**
     * 维护角色~菜单关联信息
     * @param roleId
     * @param menuIdList
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
        // 需要先清除旧的关联数据，再插入新的关联信息
        deleteBatch(Arrays.asList(roleId));
        SysRoleMenuEntity entity;
        if (menuIdList!=null && !menuIdList.isEmpty()){
            for (Long mId:menuIdList){
                entity=new SysRoleMenuEntity();
                entity.setRoleId(roleId);
                entity.setMenuId(mId);
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
     * 获取角色对应的菜单列表
     * @param roleId
     * @return
     */
    @Override
    public List<Long> queryMenuIdList(Long roleId) {
        return baseMapper.queryMenuIdList(roleId);
    }
}
