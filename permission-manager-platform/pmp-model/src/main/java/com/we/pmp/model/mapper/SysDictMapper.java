package com.we.pmp.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.we.pmp.model.entity.SysDictEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据字典Mapper
 * @author we
 * @date 2021-05-07 16:50
 **/
@Mapper
public interface SysDictMapper extends BaseMapper<SysDictEntity> {
}
