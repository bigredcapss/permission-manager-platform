package com.we.pmp.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.we.pmp.model.entity.AttendRecordEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 考勤记录Mapper
 * @author we
 * @date 2021-05-07 09:14
 **/
@Mapper
public interface AttendRecordMapper extends BaseMapper<AttendRecordEntity> {
    /**
     * 分页查询
     * @param page
     * @param paramMap
     * @return
     */
    List<AttendRecordEntity> queryPage(IPage<AttendRecordEntity> page, @Param("paramMap") Map<String,Object> paramMap);

    /**
     * 查询导出数据
     * @param params
     * @return
     */
    List<AttendRecordEntity> selectExportData(Map<String, Object> params);


}
