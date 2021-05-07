package com.we.pmp.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.we.pmp.model.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志Mapper
 * @author we
 * @date 2021-05-07 16:51
 **/
@Mapper
public interface SysLogMapper extends BaseMapper<SysLogEntity> {

    void truncate();
}
