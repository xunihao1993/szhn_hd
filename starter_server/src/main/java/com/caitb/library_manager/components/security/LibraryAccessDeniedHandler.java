package com.caitb.library_manager.components.security;

import com.alibaba.fastjson.JSON;
import com.caitb.library_manager.dto.R;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author
 */
@Component
public class LibraryAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        R resultVo = R.error("无权限访问");
        resultVo.setMessage("无权访问!");
        resultVo.setCode(403);
        resultVo.getErrorInfo().setErrCode("40300");
        resultVo.setSuccess(true);

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(JSON.toJSONString(resultVo));
    }

}

