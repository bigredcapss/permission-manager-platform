package com.we.pmp.server.web.sercice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.we.pmp.common.response.PageResult;
import com.we.pmp.model.entity.AttendRecordEntity;

import java.util.List;
import java.util.Map;

/**
 * 考勤记录Service
 * @author we
 * @date 2021-05-08 09:08
 **/
public interface IAttendRecordService extends IService<AttendRecordEntity> {
    /**
     * 分页查询考勤记录
     * @param params
     * @return
     */
    PageResult queryPage(Map<String, Object> params);

    /**
     * 获取所有的考勤记录
     * @param params
     * @return
     */
    List<AttendRecordEntity> selectAll(Map<String, Object> params);

    /**
     * 管理导出考勤记录
     * @param list
     * @return
     */
    List<Map<Integer, Object>> manageExport(List<AttendRecordEntity> list);
}
