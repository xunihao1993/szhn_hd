package com.caitb.library_manager.components.security.component;

import com.alibaba.fastjson.JSON;
import com.caitb.library_manager.consts.CommonConstants;
import com.caitb.library_manager.dto.R;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 功能描述: 客户端异常处理 可以根据 AuthenticationException 不同细化异常处理
 * <p>
 */
@Component
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding(CommonConstants.UTF8);
        response.setContentType(CommonConstants.CONTENT_TYPE);
        response.setStatus(HttpServletResponse.SC_OK);

        R result = new R().setCode(HttpServletResponse.SC_UNAUTHORIZED);
        if (authException != null) {
            result.setMessage(authException.getMessage());
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(JSON.toJSONString(result));
    }
}
