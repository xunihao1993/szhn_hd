package com.caitb.library_manager.base.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @TableName sys_user
 */
@TableName(value ="sys_user")
@Data
public class SysUser implements Serializable {
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
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    private String mobile;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户的真实姓名
     */
    private String realName;

    /**
     * 用户的性别 0代表男 1代表女 2代表未知
     */
    private Boolean sex;

    /**
     * 用户的头像地址
     */
    private String avatar;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 城镇
     */
    private String town;

    /**
     * 地址
     */
    private String address;

    /**
     * 账号的状态 0代表正常 1代表被冻结
     */
    private Integer state;

    @TableField(exist = false)
    private List<String> roleList = new ArrayList<>();

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}