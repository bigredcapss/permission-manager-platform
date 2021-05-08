package com.we.pmp.server.web.sercice;

import java.util.Set;

/**
 * 自定义通用的部门数据权限控制service
 * @author we
 * @date 2021-05-08 09:03
 **/
public interface ICommonDataService {
    Set<Long> getCurrUserDataDeptIds();

    String getCurrUserDataDeptIdsStr();

}
