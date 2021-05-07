package com.we.pmp.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.we.pmp.model.entity.SysDeptEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 部门管理Mapper
 * @author we
 * @date 2021-05-07 16:47
 **/
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDeptEntity> {

    List<SysDeptEntity> queryList(Map<String, Object> params);

    List<SysDeptEntity> queryListV2(Map<String, Object> params);

    /**
     * 根据父级部门id查询子部门id列表
     * @param parentId
     * @return
     */
    List<Long> queryDeptIds(Long parentId);
    /**
     * 查询部门Id列表
     * @return
     */
    Set<Long> queryAllDeptIds();
}
