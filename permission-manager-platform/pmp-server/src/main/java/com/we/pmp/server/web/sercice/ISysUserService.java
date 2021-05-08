package com.we.pmp.server.web.sercice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.we.pmp.common.response.PageResult;
import com.we.pmp.model.entity.SysUserEntity;

import java.util.Map;

/**
 * 用户管理Service
 * @author we
 * @date 2021-05-08 08:43
 **/
public interface ISysUserService extends IService<SysUserEntity> {
    /**
     * 修改密码
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    boolean updatePassword(Long userId,String oldPassword,String newPassword);
    /**
     * 分页查询用户
     * @param map
     * @return
     */
    PageResult queryPage(Map<String,Object> map);

    /**
     * 新增用户
     * @param entity
     */
    void saveUser(SysUserEntity entity);
    /**
     * 根据用户Id获取用户信息
     * @param userId
     * @return
     */
    SysUserEntity getInfo(Long userId);

    /**
     * 更新用户信息
     * @param entity
     */
    void updateUser(SysUserEntity entity);

    /**
     * 批量删除用户
     * @param ids
     */
    void deleteUser(Long[] ids);

    /**
     * 批量更新密码
     * @param ids
     */
    void updatePsd(Long[] ids);

}
