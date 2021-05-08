package com.we.pmp.server.web.sercice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Joiner;
import com.we.pmp.common.response.PageResult;
import com.we.pmp.common.response.StatusCode;
import com.we.pmp.common.utils.CommonUtil;
import com.we.pmp.common.utils.QueryUtil;
import com.we.pmp.model.entity.SysPostEntity;
import com.we.pmp.model.mapper.SysPostMapper;
import com.we.pmp.server.web.sercice.ISysPostService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 岗位管理接口实现
 * @author we
 * @date 2021-05-08 09:38
 **/
@Service
@Slf4j
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPostEntity> implements ISysPostService {
    /**
     * 分页模糊查询
     * @param params
     * @return
     */
    @Override
    public PageResult queryPage(Map<String, Object> params) {
        String search= (params.get("search") == null)? "": params.get("search").toString() ;
        // 调用自封装的分页查询工具
        IPage<SysPostEntity> queryPage=new QueryUtil<SysPostEntity>().getQueryPage(params);
        QueryWrapper wrapper=new QueryWrapper<SysPostEntity>()
                .like(StringUtils.isNotBlank(search),"post_code",search.trim())
                .or(StringUtils.isNotBlank(search))
                .like(StringUtils.isNotBlank(search),"post_name",search.trim());
        IPage<SysPostEntity> resPage=this.page(queryPage,wrapper);
        return new PageResult(resPage);
    }

    /**
     * 新增岗位信息
     * @param entity
     */
    @Override
    public void savePost(SysPostEntity entity) {
        if(this.getOne(new QueryWrapper<SysPostEntity>().eq("post_code",entity.getPostCode()))!=null){
            throw new RuntimeException(StatusCode.PostCodeHasExist.getMsg());
        }
        entity.setCreateTime(DateTime.now().toDate());
        save(entity);
    }

    /**
     * 更新岗位信息
     * @param entity
     */
    @Override
    public void updatePost(SysPostEntity entity) {
        SysPostEntity old=this.getById(entity.getPostId());
        if (old!=null && !old.getPostCode().equals(entity.getPostCode())){
            if (this.getOne(new QueryWrapper<SysPostEntity>().eq("post_code",entity.getPostCode()))!=null){
                throw new RuntimeException(StatusCode.PostCodeHasExist.getMsg());
            }
        }
        entity.setUpdateTime(DateTime.now().toDate());
        updateById(entity);
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void deletePatch(Long[] ids) {
        // 第一种写法 - mybatis-plus
        //removeByIds(Arrays.asList(ids));

        // 第二种写法
        //ids=[1,2,3,4,5];  ->  "1,2,3,4,5"
        String delIds= Joiner.on(",").join(ids);
        baseMapper.deleteBatch(CommonUtil.concatStrToInt(delIds,","));
    }
}
