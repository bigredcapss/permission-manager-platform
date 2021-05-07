package com.we.pmp.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统角色Entity
 * @author we
 * @date 2021-05-07 09:11
 **/
@Data
@TableName("sys_role")
public class SysRoleEntity implements Serializable {
    @TableId
    private Long roleId;
    @NotBlank(message="角色名称不能为空")
    private String roleName;
    private String remark;
    @TableField(exist=false)
    private List<Long> menuIdList;
    @TableField(exist=false)
    private List<Long> deptIdList;
    private Date createTime;
}
