package com.caitb.library_manager.components.security;

import com.caitb.library_manager.dto.AuthUserInfoVo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 功能描述: 封装登录用户信息
 * <p>
 */
public class CustomUserDetail extends User {

    private static final long serialVersionUID = 2554579244118504774L;
    private final AuthUserInfoVo authUserInfoVo;

    public CustomUserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities, AuthUserInfoVo authUserInfoVo) {
        super(username, password, authorities);
        this.authUserInfoVo = authUserInfoVo;
    }

    CustomUserDetail(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, AuthUserInfoVo authUserInfoVo) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.authUserInfoVo = authUserInfoVo;
    }

    public AuthUserInfoVo getAuthUserInfoVo() {
        return authUserInfoVo;
    }
}
