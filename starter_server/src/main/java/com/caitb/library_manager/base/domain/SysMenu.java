package com.caitb.library_manager.base.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName sys_menu
 */
@TableName(value ="sys_menu")
@Data
public class SysMenu implements Serializable {
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
    private Integer isDelete;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单code
     */
    private String code;

    /**
     * 菜单对应的路径
     */
    private String path;

    /**
     * 菜单对应的文件路径
     */
    private String filePath;

    /**
     * 菜单的类型 0代表页面 1代表按钮
     */
    private Integer type;

    /**
     * 是否显示 0代表是 1代表否
     */
    private Integer showFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}