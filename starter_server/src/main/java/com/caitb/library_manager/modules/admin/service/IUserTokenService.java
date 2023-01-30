package com.caitb.library_manager.modules.admin.service;

import com.caitb.library_manager.base.domain.SysUser;
import com.caitb.library_manager.dto.R;
import com.caitb.library_manager.params.LoginParam;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能描述: 用户登录认证 业务层
 * <p>
 */
public interface IUserTokenService {

    /**
     * 登录接口
     * @param param
     * @param request
     * @return
     */
    R login(LoginParam param, HttpServletRequest request);

    /**
     * 创建token
     * @param user
     * @param client
     * @return
     */
    OAuth2AccessToken createToken(SysUser user, ClientDetails client);

    /**
     * 退出登录
     * @return
     */
    R logout();
}