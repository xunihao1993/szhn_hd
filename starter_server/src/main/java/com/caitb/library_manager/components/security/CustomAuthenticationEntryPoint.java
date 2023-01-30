package com.caitb.library_manager.components.security;

import com.alibaba.fastjson.JSON;
import com.caitb.library_manager.dto.R;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 功能描述: 认证失败自定义处理
 * <p>
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setCharacterEncoding("UTF-8");

        R apiResult = R.error("权限不足");

        PrintWriter printWriter = response.getWriter();
        printWriter.print(JSON.toJSONString(apiResult));
        printWriter.flush();
        printWriter.close();
    }
}
