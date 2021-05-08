package com.we.pmp.server.web.sercice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.we.pmp.model.entity.SysUserPostEntity;

import java.util.List;

/**
 * 用户-岗位关系Service
 * @author we
 * @date 2021-05-08 08:55
 **/
public interface ISysUserPostService extends IService<SysUserPostEntity> {
    /**
     * 根据用户Id查询岗位名称
     * @param userId
     * @return
     */
    String getPostNameByUserId(Long userId);

    /**
     * 保存或更新岗位名称
     * @param userId
     * @param postIds
     */
    void saveOrUpdate(Long userId, List<Long> postIds);

    /**
     * 根据用户Id查询岗位Id列表
     * @param userId
     * @return
     */
    List<Long> queryPostIdList(Long userId);
}
