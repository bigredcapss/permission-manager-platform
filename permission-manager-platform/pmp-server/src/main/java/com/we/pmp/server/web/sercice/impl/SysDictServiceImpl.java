package com.we.pmp.server.web.sercice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.we.pmp.common.response.PageResult;
import com.we.pmp.common.utils.QueryUtil;
import com.we.pmp.model.entity.SysDictEntity;
import com.we.pmp.model.mapper.SysDictMapper;
import com.we.pmp.server.web.sercice.ISysDictService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 数据字典接口实现
 * @author we
 * @date 2021-05-08 09:32
 **/
@Service
@Slf4j
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDictEntity> implements ISysDictService {
    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageResult queryPage(Map<String, Object> params) {
        String name = (String)params.get("name");
        IPage queryPage=new QueryUtil<SysDictEntity>().getQueryPage(params);
        // 查询包装器
        QueryWrapper<SysDictEntity> wrapper=new QueryWrapper<SysDictEntity>()
                .like(StringUtils.isNotBlank(name),"name", name);
        IPage<SysDictEntity> page=this.page(queryPage,wrapper);
        return new PageResult(page);
    }
}
