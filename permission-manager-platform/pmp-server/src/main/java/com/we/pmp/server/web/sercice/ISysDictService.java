package com.we.pmp.server.web.sercice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.we.pmp.common.response.PageResult;
import com.we.pmp.model.entity.SysDictEntity;

import java.util.Map;

/**
 * 数据字典Service
 * @author we
 * @date 2021-05-08 09:10
 **/
public interface ISysDictService extends IService<SysDictEntity> {
    /**
     * 带条件的分页查询
     * @param params
     * @return
     */
    PageResult queryPage(Map<String, Object> params);
}
