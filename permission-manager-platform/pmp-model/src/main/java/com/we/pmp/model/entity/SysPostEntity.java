package com.we.pmp.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

/**
 * 岗位Entity
 * @author we
 * @date 2021-05-07 09:09
 **/
@Data
@TableName("sys_post")
public class SysPostEntity implements Serializable {
    @TableId
    private Long postId;
    @NotBlank(message = "岗位编码不能为空!")
    private String postCode;
    @NotBlank(message = "岗位名称不能为空!")
    private String postName;
    private Integer orderNum;
    private Integer status;
    private Date createTime;
    private Date updateTime;
    private String remark;
}
