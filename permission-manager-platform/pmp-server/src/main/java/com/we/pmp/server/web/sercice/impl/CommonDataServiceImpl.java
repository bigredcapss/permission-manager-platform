package com.we.pmp.server.web.sercice.impl;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import com.we.pmp.common.utils.CommonUtil;
import com.we.pmp.common.utils.Constant;
import com.we.pmp.model.entity.SysUserEntity;
import com.we.pmp.model.mapper.SysDeptMapper;
import com.we.pmp.model.mapper.SysUserMapper;
import com.we.pmp.server.support.shiro.ShiroUtil;
import com.we.pmp.server.web.sercice.ICommonDataService;
import com.we.pmp.server.web.sercice.ISysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 通用的部门数据权限控制service
 * @author we
 * @date 2021-05-08 09:04
 **/
@Service
@Slf4j
public class CommonDataServiceImpl implements ICommonDataService {
    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private ISysDeptService sysDeptService;

    /**
     * 获取当前登录用户的部门数据Id列表
     * @return
     */
    @Override
    public Set<Long> getCurrUserDataDeptIds() {
        Set<Long> dataIds= Sets.newHashSet();
        SysUserEntity currUser= ShiroUtil.getUserEntity();
        if (Constant.SUPER_ADMIN.equals(currUser.getUserId())){
            dataIds=sysDeptMapper.queryAllDeptIds();
        }else{
            // 分配给用户的部门数据权限id列表
            Set<Long> userDeptDataIds=sysUserMapper.queryDeptIdsByUserId(currUser.getUserId());
            if (userDeptDataIds!=null && !userDeptDataIds.isEmpty()){
                dataIds.addAll(userDeptDataIds);
            }
            // 用户所在的部门及其子部门Id列表 ~ 递归实现
            dataIds.add(currUser.getDeptId());
            List<Long> subDeptIdList=sysDeptService.getSubDeptIdList(currUser.getDeptId());
            dataIds.addAll(Sets.newHashSet(subDeptIdList));
        }
        return dataIds;
    }

    /**
     * 将 部门数据Id列表 转化为 id拼接 的字符串
     * @return
     */
    @Override
    public String getCurrUserDataDeptIdsStr() {
        String result=null;
        Set<Long> dataSet=this.getCurrUserDataDeptIds();
        if (dataSet!=null && !dataSet.isEmpty()){
            result= CommonUtil.concatStrToInt(Joiner.on(",").join(dataSet),",");
        }
        return result;
    }
}
