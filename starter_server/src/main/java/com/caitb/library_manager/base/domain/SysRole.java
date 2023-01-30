package com.caitb.library_manager.base.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName sys_role
 */
@TableName(value ="sys_role")
@Data
public class SysRole implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 删除标识 0代表正常 1代表已删除
     */
    private Boolean isDelete;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色的code
     */
    private String code;

    /**
     * 简介
     */
    private String descInfo;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}