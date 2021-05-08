package com.we.pmp.server.web.sercice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.we.pmp.common.utils.Constant;
import com.we.pmp.model.entity.SysDeptEntity;
import com.we.pmp.model.mapper.SysDeptMapper;
import com.we.pmp.server.support.shiro.ShiroUtil;
import com.we.pmp.server.web.sercice.ICommonDataService;
import com.we.pmp.server.web.sercice.ISysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 部门管理接口实现
 * @author we
 * @date 2021-05-08 09:00
 **/
@Service("sysDeptService")
@Slf4j
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDeptEntity> implements ISysDeptService {

    @Autowired
    private ICommonDataService commonDataService;

    /**
     * 查询所有部门列表 ~ 涉及到 部门数据权限 的控制
     * @param map
     * @return
     */
    @Override
    public List<SysDeptEntity> queryAll(Map<String, Object> map) {
        if (!ShiroUtil.getUserId().equals(Constant.SUPER_ADMIN)){
            String deptDataIds=commonDataService.getCurrUserDataDeptIdsStr();
            map.put("deptDataIds",(StringUtils.isNotBlank(deptDataIds))?deptDataIds:null);
        }
        return baseMapper.queryListV2(map);
    }

    /**
     * 根据父级部门id查询子部门id列表
     * @param parentId
     * @return
     */
    @Override
    public List<Long> queryDeptIds(Long parentId) {
        return baseMapper.queryDeptIds(parentId);
    }

    /**
     * 获取当前部门的子部门id列表
     * @param deptId
     * @return
     */
    @Override
    public List<Long> getSubDeptIdList(Long deptId) {
        List<Long> deptIdList= Lists.newLinkedList();
        // 第一级部门Id列表
        List<Long> subIdList=baseMapper.queryDeptIds(deptId);
        getDeptTreeList(subIdList,deptIdList);

        return deptIdList;
    }

    /**
     * 递归
     * @param subIdList 第一级部门数据Id列表
     * @param deptIdList 每次递归时循环存储的结果数据Id列表
     */
    private void getDeptTreeList(List<Long> subIdList,List<Long> deptIdList){
        List<Long> list;
        for (Long subId:subIdList){
            list=baseMapper.queryDeptIds(subId);
            if (list!=null && !list.isEmpty()){
                // 调用递归之处
                getDeptTreeList(list,deptIdList);
            }
            // 执行到这里时，就表示当前递归结束
            deptIdList.add(subId);
        }
    }
}
