package com.we.pmp.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户-岗位关系Entity
 * @author we
 * @date 2021-05-07 09:13
 **/
@Data
@TableName("sys_user_post")
public class SysUserPostEntity implements Serializable {
    @TableId
    private Long id;
    /**
     * 用户Id
     */
    private Long userId;
    /**
     * 岗位Id
     */
    private Long postId;
    @TableField(exist = false)
    private String postName;
}
