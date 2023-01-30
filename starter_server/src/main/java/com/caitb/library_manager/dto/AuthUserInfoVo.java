package com.caitb.library_manager.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能描述: 认证缓存的Bean类
 * <p>
 * 作者: July
 * 日期: 2019-03-01 12:00
 */
@Data
@Accessors(chain = true)
public class AuthUserInfoVo implements Serializable {

    private static final long serialVersionUID = -1772287499042143185L;

    private Long id;
    private String nickName;
    private String mobile;

    private Date createTime;
}
