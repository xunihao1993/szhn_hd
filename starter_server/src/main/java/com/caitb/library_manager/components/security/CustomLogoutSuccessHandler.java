package com.caitb.library_manager.components.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 功能描述: 退出系统自定义处理
 * <p>
 */
@Component
public class CustomLogoutSuccessHandler implements AuthenticationEntryPoint {

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String access_token = request.getParameter("access_token");
        if (access_token != null) {
            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(access_token);
            tokenStore.removeAccessToken(oAuth2AccessToken);
        }
    }
}
