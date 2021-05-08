package com.we.pmp.server.web.sercice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.we.pmp.common.response.PageResult;
import com.we.pmp.common.utils.QueryUtil;
import com.we.pmp.model.entity.SysLogEntity;
import com.we.pmp.model.mapper.SysLogMapper;
import com.we.pmp.server.web.sercice.ISysLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 日志接口实现
 * @author we
 * @date 2021-05-07 20:49
 **/
@Service
@Slf4j
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLogEntity> implements ISysLogService {

    /**
     * 分页模糊查询
     * @param params
     * @return
     */
    @Override
    public PageResult queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        IPage<SysLogEntity> queryPage = new QueryUtil<SysLogEntity>().getQueryPage(params);
        QueryWrapper<SysLogEntity> wrapper = new QueryWrapper<SysLogEntity>()
                .like(StringUtils.isNotBlank(key), "username", key)
                .or(StringUtils.isNotBlank(key))
                .like(StringUtils.isNotBlank(key), "operation", key);
        IPage<SysLogEntity> page = this.page(queryPage, wrapper);
        return new PageResult(page);
    }

    /**
     * 清空日志表
     */
    @Override
    public void truncate() {
        baseMapper.truncate();
    }
}
