package com.we.pmp.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 考勤记录Entity
 * @author we
 * @date 2021-05-07 09:06
 **/
@Data
@TableName("attend_record")
public class AttendRecordEntity implements Serializable {
    @TableId
    private Integer id;
    private Long userId;
    private Long deptId;
    private Date startTime;
    private Date endTime;
    private BigDecimal total;
    private Byte status;
    private Date createTime;
    private Date updateTime;
    @TableField(exist = false)
    private String userName;
    @TableField(exist = false)
    private String deptName;
}
