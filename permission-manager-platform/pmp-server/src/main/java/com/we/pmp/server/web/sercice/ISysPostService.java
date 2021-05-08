package com.we.pmp.server.web.sercice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.we.pmp.common.response.PageResult;
import com.we.pmp.model.entity.SysPostEntity;

import java.util.Map;

/**
 * 岗位管理Service
 * @author we
 * @date 2021-05-08 09:16
 **/
public interface ISysPostService extends IService<SysPostEntity> {
    /**
     * 分页模糊查询
     * @param params
     * @return
     */
    PageResult queryPage(Map<String,Object> params);

    /**
     * 新增岗位信息
     * @param entity
     */
    void savePost(SysPostEntity entity);

    /**
     * 更新岗位信息
     * @param entity
     */
    void updatePost(SysPostEntity entity);

    /**
     * 批量删除岗位信息
     * @param ids
     */
    void deletePatch(Long[] ids);
}
