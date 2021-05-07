package com.we.pmp.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色-部门关系Entity
 * @author we
 * @date 2021-05-07 09:10
 **/
@Data
@TableName("sys_role_dept")
public class SysRoleDeptEntity implements Serializable {
    @TableId
    private Long id;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 部门ID
     */
    private Long deptId;
}
