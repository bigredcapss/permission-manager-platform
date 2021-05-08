package com.we.pmp.server.web.sercice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.we.pmp.common.response.PageResult;
import com.we.pmp.common.utils.Constant;
import com.we.pmp.common.utils.QueryUtil;
import com.we.pmp.model.entity.SysDeptEntity;
import com.we.pmp.model.entity.SysUserEntity;
import com.we.pmp.model.entity.SysUserPostEntity;
import com.we.pmp.model.entity.SysUserRoleEntity;
import com.we.pmp.model.mapper.SysUserMapper;
import com.we.pmp.server.support.shiro.ShiroUtil;
import com.we.pmp.server.web.sercice.ISysDeptService;
import com.we.pmp.server.web.sercice.ISysUserPostService;
import com.we.pmp.server.web.sercice.ISysUserRoleService;
import com.we.pmp.server.web.sercice.ISysUserService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户接口实现
 * @author we
 * @date 2021-05-08 08:46
 **/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements ISysUserService {
    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private ISysUserPostService sysUserPostService;
    @Autowired
    private ISysUserRoleService sysUserRoleService;

    /**
     * 更新密码 ~ 借助于mybatis-plus的方法来实现
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @Override
    public boolean updatePassword(Long userId, String oldPassword, String newPassword) {
        SysUserEntity entity=new SysUserEntity();
        entity.setPassword(newPassword);
        Boolean res=this.update(entity,new QueryWrapper<SysUserEntity>().eq("user_id",userId).eq("password",oldPassword));
        return res;
    }

    /**
     * 列表分页模糊查询
     * @param map
     * @return
     */
    @Override
    public PageResult queryPage(Map<String, Object> map) {
        String search=(map.get("username")!=null)? (String) map.get("username") :"";
        IPage<SysUserEntity> iPage=new QueryUtil<SysUserEntity>().getQueryPage(map);
        QueryWrapper wrapper=new QueryWrapper<SysUserEntity>()
                .like(StringUtils.isNotBlank(search),"username",search.trim())
                .or(StringUtils.isNotBlank(search.trim()))
                .like(StringUtils.isNotBlank(search),"name",search.trim());
        IPage<SysUserEntity> resPage=this.page(iPage,wrapper);
        // 获取用户所属的部门、用户的岗位信息
        SysDeptEntity dept;
        for (SysUserEntity user:resPage.getRecords()){
            try {
                dept=sysDeptService.getById(user.getDeptId());
                user.setDeptName((dept!=null && StringUtils.isNotBlank(dept.getName()))? dept.getName() : "");
                String postName=sysUserPostService.getPostNameByUserId(user.getUserId());
                user.setPostName(postName);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return new PageResult(resPage);
    }

    /**
     * 新增用户信息
     * @param entity
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUser(SysUserEntity entity) {
        if (this.getOne(new QueryWrapper<SysUserEntity>().eq("username",entity.getUsername())) !=null ){
            throw new RuntimeException("用户名已存在!");
        }
        entity.setCreateTime(new Date());
        // 加密密码串
        String salt= RandomStringUtils.randomAlphanumeric(20);
        String password= ShiroUtil.sha256(entity.getPassword(),salt);
        entity.setPassword(password);
        entity.setSalt(salt);
        this.save(entity);
        //维护好用户-角色的关联关系
        sysUserRoleService.saveOrUpdate(entity.getUserId(),entity.getRoleIdList());
        //维护好用户-岗位的关联关系
        sysUserPostService.saveOrUpdate(entity.getUserId(),entity.getPostIdList());
    }

    /**
     * 获取用户详情~包括其分配的角色、岗位关联信息
     * @param userId
     * @return
     */
    @Override
    public SysUserEntity getInfo(Long userId) {
        SysUserEntity entity=this.getById(userId);
        // 获取用户分配的角色关联信息
        List<Long> roleIds=sysUserRoleService.queryRoleIdList(userId);
        entity.setRoleIdList(roleIds);
        // 获取用户分配的岗位关联信息
        List<Long> postIds=sysUserPostService.queryPostIdList(userId);
        entity.setPostIdList(postIds);
        return entity;
    }

    /**
     * 更新用户信息
     * @param entity
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(SysUserEntity entity) {
        SysUserEntity old=this.getById(entity.getUserId());
        if (old==null){
            return;
        }
        if (!old.getUsername().equals(entity.getUsername())){
            if (this.getOne(new QueryWrapper<SysUserEntity>().eq("username",entity.getUsername())) !=null ){
                throw new RuntimeException("修改后的用户名已存在!");
            }
        }
        if (StringUtils.isNotBlank(entity.getPassword())){
            String password=ShiroUtil.sha256(entity.getPassword(),old.getSalt());
            entity.setPassword(password);
//            String newSalt=RandomStringUtils.randomAlphanumeric(20);
//            String password=ShiroUtil.sha256(entity.getPassword(),newSalt);
//            entity.setPassword(password);
//            entity.setSalt(newSalt);
        }
        this.updateById(entity);
        //维护好用户-角色的关联关系
        sysUserRoleService.saveOrUpdate(entity.getUserId(),entity.getRoleIdList());
        //维护好用户-岗位的关联关系
        sysUserPostService.saveOrUpdate(entity.getUserId(),entity.getPostIdList());
    }

    /**
     * 删除用户：除了删除 用户本身的 信息之外，还需要删除用户-角色、用户-岗位 关联关系信息
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Long[] ids) {
        if (ids!=null && ids.length>0){
            List<Long> userIds= Arrays.asList(ids);
            this.removeByIds(userIds);
            userIds.stream().forEach(uId -> sysUserRoleService.remove(new QueryWrapper<SysUserRoleEntity>().eq("user_id",uId)));
            userIds.stream().forEach(uId -> sysUserPostService.remove(new QueryWrapper<SysUserPostEntity>().eq("user_id",uId)));
        }
    }

    /**
     * 重置密码
     * @param ids
     */
    @Override
    public void updatePsd(Long[] ids) {
        if (ids!=null && ids.length>0){
            SysUserEntity entity;
            for (Long uId:ids){
                entity=new SysUserEntity();
                String salt=RandomStringUtils.randomAlphanumeric(20);
                String newPsd=ShiroUtil.sha256(Constant.DefaultPassword,salt);
                entity.setPassword(newPsd);
                entity.setSalt(salt);
                this.update(entity,new QueryWrapper<SysUserEntity>().eq("user_id",uId));
            }
        }
    }
}
