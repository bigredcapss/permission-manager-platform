package com.we.pmp.server.web.sercice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.we.pmp.model.entity.SysDeptEntity;

import java.util.List;
import java.util.Map;

/**
 * 部门管理Service
 * @author we
 * @date 2021-05-08 08:49
 **/
public interface ISysDeptService extends IService<SysDeptEntity> {
    /**
     * 查询所有部门信息
     * @param map
     * @return
     */
    List<SysDeptEntity> queryAll(Map<String,Object> map);

    /**
     * 根据上级部门Id获取下级部门id列表
     * @param parentId
     * @return
     */
    List<Long> queryDeptIds(Long parentId);

    /**
     * 获取当前部门的子部门id列表
     * @param deptId
     * @return
     */
    List<Long> getSubDeptIdList(Long deptId);
}
