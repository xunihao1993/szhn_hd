package com.caitb.library_manager.components.security;

import com.caitb.library_manager.base.domain.SysMenu;
import com.caitb.library_manager.base.domain.SysRole;
import com.caitb.library_manager.base.domain.SysUser;
import com.caitb.library_manager.base.service.SysUserService;
import com.caitb.library_manager.consts.CommonConstants;
import com.caitb.library_manager.dto.AuthUserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 功能描述: 封装登录用户信息
 * <p>
 */
@Slf4j
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private SysUserService userService;

    /**
     * 功能描述: 获取用户信息 返回 {@link UserDetails}
     * <p>
     * 作者: July
     * 日期: 2019-03-01 11:59:28
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.queryOneByAccount(username);
        if (user == null) {
            log.error("当前用户不存在:param:{}", username);
            throw new UsernameNotFoundException("当前用户不存在");
        }
        return createDetailByUser(user, CommonConstants.AUTH_CLIENT_ID);
    }

    public UserDetails createDetailByUser(SysUser user, String clientId) {
        // 添加用户权限
        Set<GrantedAuthority> grantedAuthorities = getGrantedAuthorities(clientId, user.getId());

        // 添加返回的部分用户其他的信息
        AuthUserInfoVo authUserInfoVo = getAuthUserInfoVo(user);
        // 生成自定义用户描述信息
        return new CustomUserDetail(
                user.getUsername(),
                user.getPassword(),
                user.getState() == 0,  //账号状态  0 表示正常  1表示停用
                true,
                true,
                true,
                grantedAuthorities,
                authUserInfoVo
        );
    }

    private Set<GrantedAuthority> getGrantedAuthorities(String clientId, Long userId) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // 默认添加用户权限
//        if (CommonConstants.AUTH_CLIENT_ID_MOBILE.equalsIgnoreCase(clientId)) {
//            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + CommonConstants.AUTH_ROLE_CLIENT_ID_MOBILE));
//        }

        // 得到用户的角色和菜单权限
        List<SysRole> userRole = userService.getUserRole(userId);
        if (!CollectionUtils.isEmpty(userRole)) {
            grantedAuthorities.addAll(userRole.stream().map(item -> new SimpleGrantedAuthority("ROLE_" + item.getCode())).collect(Collectors.toSet()));

            List<Long> roleIds = userRole.stream().map(SysRole::getId).collect(Collectors.toList());
            List<SysMenu> userPermission = userService.getUserPermission(roleIds);
            if (!CollectionUtils.isEmpty(userPermission)) {
                grantedAuthorities.addAll(userPermission.stream().map(item -> new SimpleGrantedAuthority(item.getCode())).collect(Collectors.toSet()));
            }
        }

        return grantedAuthorities;
    }

    private AuthUserInfoVo getAuthUserInfoVo(SysUser user) {
        AuthUserInfoVo infoVo = new AuthUserInfoVo();
        infoVo.setId(user.getId());
        infoVo.setMobile(user.getMobile());
        infoVo.setNickName(user.getNickName());

        infoVo.setCreateTime(user.getCreateTime());
        return infoVo;
    }
}
