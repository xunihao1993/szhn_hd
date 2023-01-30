package com.caitb.library_manager.utils;

import com.caitb.library_manager.components.security.CustomUserDetail;
import com.caitb.library_manager.dto.AuthUserInfoVo;
import com.caitb.library_manager.exception.BusinessException;
import com.caitb.library_manager.vo.UserInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * @author
 */
public class UserUtil {

    public static Logger logger = LoggerFactory.getLogger("UserUtil");

    /**
     * 得到用户信息
     * @return
     */
    public static UserInfoVo getUserInfo() {
        try {
            UserInfoVo result = new UserInfoVo();
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                throw new BusinessException("该用户不存在");
            }
            // 添加返回的会员部分信息
            Object principal = authentication.getPrincipal();
            if (principal instanceof CustomUserDetail) {
                CustomUserDetail userDetails = (CustomUserDetail) principal;
                AuthUserInfoVo authUserInfoVo = userDetails.getAuthUserInfoVo();
                result.setId(authUserInfoVo.getId());
                result.setNickName(authUserInfoVo.getNickName());
                result.setUsername(userDetails.getUsername());
            }

            // 添加权限信息
            result.setAuthorities(AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
            return result;
        } catch (Exception e) {
            logger.error("UserUtil:getUserInfo:message:{}", e.getMessage());
            return null;
        }

    }

}
