package com.caitb.library_manager.vo;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.caitb.library_manager.base.domain.LibraryBook;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @author
 */
@Data
public class LibraryBookBorrowLogVo {

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
     * 用户id
     */
    private Long userId;

    /**
     * 用户名称(不是账户名)
     */
    private String userName;

    /**
     * LOAN_OUT代表借出 LOAN_IN代表归还
     */
    private String typeFlag;

    /**
     * 书籍信息
     */
    private String bookInfo;

    /**
     * 书籍信息
     */
    private LibraryBook bookInfoDto;

    public LibraryBook getBookInfoDto() {
        if (StringUtils.isBlank(this.bookInfo)) {
            return new LibraryBook();
        }

        return JSON.parseObject(this.bookInfo, LibraryBook.class);
    }
}
