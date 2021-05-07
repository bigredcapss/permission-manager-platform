package com.we.pmp.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色-菜单关系Entity
 * @author we
 * @date 2021-05-07 09:11
 **/
@Data
@TableName("sys_role_menu")
public class SysRoleMenuEntity implements Serializable {
    @TableId
    private Long id;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 菜单ID
     */
    private Long menuId;
}
