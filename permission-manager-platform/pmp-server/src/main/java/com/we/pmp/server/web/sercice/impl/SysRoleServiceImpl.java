package com.we.pmp.server.web.sercice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.we.pmp.common.response.PageResult;
import com.we.pmp.common.utils.QueryUtil;
import com.we.pmp.model.entity.SysRoleEntity;
import com.we.pmp.model.mapper.SysRoleMapper;
import com.we.pmp.server.web.sercice.ISysRoleDeptService;
import com.we.pmp.server.web.sercice.ISysRoleMenuService;
import com.we.pmp.server.web.sercice.ISysRoleService;
import com.we.pmp.server.web.sercice.ISysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 角色接口实现
 * @author we
 * @date 2021-05-08 09:43
 **/
@Service
@Slf4j
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity> implements ISysRoleService {
    @Autowired
    private ISysRoleMenuService sysRoleMenuService;
    @Autowired
    private ISysRoleDeptService sysRoleDeptService;
    @Autowired
    private ISysUserRoleService sysUserRoleService;

    /**
     * 分页列表模糊查询
     * @param map
     * @return
     */
    @Override
    public PageResult queryPage(Map<String, Object> map) {
        String search= (map.get("search")!=null)? (String) map.get("search") : "";
        IPage<SysRoleEntity> iPage=new QueryUtil<SysRoleEntity>().getQueryPage(map);
        QueryWrapper wrapper=new QueryWrapper<SysRoleEntity>()
                .like(StringUtils.isNotBlank(search),"role_name",search);
        IPage<SysRoleEntity> resPage=this.page(iPage,wrapper);
        return new PageResult(resPage);
    }

    /**
     * 新增角色信息
     * @param role
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRole(SysRoleEntity role) {
        role.setCreateTime(DateTime.now().toDate());
        this.save(role);
        // 插入角色-菜单关联信息
        sysRoleMenuService.saveOrUpdate(role.getRoleId(),role.getMenuIdList());
        // 插入角色-部门关联信息
        sysRoleDeptService.saveOrUpdate(role.getRoleId(),role.getDeptIdList());
    }

    /**
     * 修改角色信息
     * @param role
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(SysRoleEntity role) {
        this.updateById(role);
        // 更新角色-菜单关联信息
        sysRoleMenuService.saveOrUpdate(role.getRoleId(),role.getMenuIdList());
        // 更新角色-部门关联信息
        sysRoleDeptService.saveOrUpdate(role.getRoleId(),role.getDeptIdList());
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] ids) {
        List<Long> roleIds= Arrays.asList(ids);
        this.removeByIds(roleIds);
        // 删除角色-菜单关联数据
        sysRoleMenuService.deleteBatch(roleIds);
        // 删除角色-部门关联数据
        sysRoleDeptService.deleteBatch(roleIds);
        // 删除角色-用户关联数据
        sysUserRoleService.deleteBatch(roleIds);
    }
}
