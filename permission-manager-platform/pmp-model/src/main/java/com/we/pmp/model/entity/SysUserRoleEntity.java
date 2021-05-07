package com.we.pmp.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户-角色关系Entity
 * @author we
 * @date 2021-05-07 09:13
 **/
@Data
@TableName("sys_user_role")
public class SysUserRoleEntity implements Serializable {
    @TableId
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;
}
