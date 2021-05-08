package com.we.pmp.server.web.sercice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.we.pmp.common.response.PageResult;
import com.we.pmp.model.entity.SysLogEntity;

import java.util.Map;

/**
 * 日志Service
 * @author we
 * @date 2021-05-07 20:29
 **/
public interface ISysLogService extends IService<SysLogEntity> {

    /**
     * 分页查询日志信息
     * @param params
     * @return
     */
    PageResult queryPage(Map<String, Object> params);

    /**
     * 清空日志表
     */
    void truncate();
}
