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
 * @TableName library_book_borrow_log
 */
@TableName(value ="library_book_borrow_log")
@Data
public class LibraryBookBorrowLog implements Serializable {
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
     * 用户id
     */
    private Long userId;

    /**
     * 书籍id
     */
    private Long bookId;

    /**
     * LOAN_OUT代表借出 LOAN_IN代表归还
     */
    private String typeFlag;

    /**
     * 书籍信息
     */
    private String bookInfo;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}