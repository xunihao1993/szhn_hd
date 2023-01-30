package com.caitb.library_manager.base.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @TableName library_book
 */
@TableName(value ="library_book")
@Data
public class LibraryBook implements Serializable {
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
     * 乐观锁
     */
    private Integer version;

    /**
     * 图书名称
     */
    private String bookName;

    /**
     * 图书编号
     */
    private String code;

    /**
     * 简介
     */
    private String descInfo;

    /**
     * 作者
     */
    private String author;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 分类名称
     */
    private String bookType;


    /**
     * 书籍库存
     */
    private Integer stock;

    /**
     * 状态：0代表正常 1代表被借出
     */
    private Integer state;

    /**
     * 借出人id
     */
    private Long loanOutUserId;

    @TableField(exist = false)
    /**
     * 借出人姓名
     */
    private String loanOutUserName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}