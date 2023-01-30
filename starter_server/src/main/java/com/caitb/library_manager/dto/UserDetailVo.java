package com.caitb.library_manager.dto;

import lombok.Data;

/**
 * 功能描述: 用户详情Vo
 * <p>
 * 作者: July
 * 日期: 2019-03-22 10:48
 */
@Data
public class UserDetailVo {

    /** 用户ID */
    private Long id;

    /** 用户昵称 */
    private String nickName;

    /** 用户头像 */
    private String avatar;

    /** 用户手机 */
    private String mobile;

    /** 个性签名 */
    private String signature;

}
