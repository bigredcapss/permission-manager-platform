package com.we.pmp.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.we.pmp.model.entity.SysPostEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 岗位管理Mapper
 * @author we
 * @date 2021-05-07 16:53
 **/
@Mapper
public interface SysPostMapper extends BaseMapper<SysPostEntity> {
    /**
     * 批量删除岗位
     * @param ids
     * @return
     */
    int deleteBatch(@Param("ids") String ids);
}
