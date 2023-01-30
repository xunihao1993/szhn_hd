package com.caitb.library_manager.vo;

import lombok.Data;

import java.util.Set;

/**
 * @author
 */
@Data
public class UserInfoVo {

    /**
     * 用户名
     */
    private String username;

    /**
     * id
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 权限码
     */
    private Set<String> authorities;

}
