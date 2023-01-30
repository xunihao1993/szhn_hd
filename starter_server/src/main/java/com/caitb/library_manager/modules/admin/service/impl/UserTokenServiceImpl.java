package com.caitb.library_manager.modules.admin.service.impl;

import com.caitb.library_manager.base.domain.SysUser;
import com.caitb.library_manager.base.service.SysUserService;
import com.caitb.library_manager.components.security.CustomUserDetailService;
import com.caitb.library_manager.dto.R;
import com.caitb.library_manager.modules.admin.service.IUserTokenService;
import com.caitb.library_manager.params.LoginParam;
import com.caitb.library_manager.utils.UserUtil;
import com.caitb.library_manager.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能描述: 用户登录认证 业务实现层
 * <p>
 * 作者: July
 * 日期: 2019-04-10 18:02
 */
@Slf4j
@Service
@Transactional
public class UserTokenServiceImpl implements IUserTokenService {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private SysUserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Qualifier("libraryTokenService")
    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    private final Map<String, OAuth2Request> oAuth2RequestMap = new ConcurrentHashMap<>();


    private OAuth2Request getAccountOauth2Request(ClientDetails client) {
        OAuth2Request request = oAuth2RequestMap.getOrDefault(client.getClientId(), null);
        if (request == null) {
            Map<String, String> requestParameters = new HashMap<>();
            Map<String, Serializable> extensionProperties = new HashMap<>();
            // 生成
            request = new OAuth2Request(requestParameters, client.getClientId(), client.getAuthorities(), true, client.getScope(), client.getResourceIds(), null, null, extensionProperties);
            // 加入到Map
            oAuth2RequestMap.put(client.getClientId(), request);
        }
        return request;
    }

    @Override
    @Transactional(readOnly = true)
    public R login(LoginParam param, HttpServletRequest request) {
        if (param == null) {
            return R.error(400, "参数错误");
        }
        String username = param.getUsername();
        String password = param.getPassword();

        // 验证参数信息
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return R.error(400, "参数错误");
        }
//        // 获取授权客户端ID
        String clientId = param.getClientId();
//        if (StringUtils.isBlank(clientId) || !CommonConstants.AUTH_CLIENT_IDS.contains(clientId.toUpperCase())) {
//            return R.paramError();
//        }
//        // 获取 ClientDetails
        ClientDetails client = clientDetailsService.loadClientByClientId(clientId);

        // 查询用户信息
        SysUser user = userService.queryOneByAccount(username);
        if (user == null) {
            return R.error("账号不存在或密码错误");
        } else if (user.getState() != 0) {
            return R.error("账号已被禁用");
        }

        // 开始处理登录流程
            // 校验密码是否正确
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return R.error("密码不正确");
        }

        // 生成token
        OAuth2AccessToken token = createToken(user, client);
        if (token != null) {
            return R.ok(token);
        }
        return R.error("服务器忙,请稍后再试");
    }

    @Override
    @Transactional(readOnly = true)
    public OAuth2AccessToken createToken(SysUser user, ClientDetails client) {
        if (user == null) {
            return null;
        }

        // 检测清理以前的令牌信息
        Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientIdAndUserName(client.getClientId(), user.getUsername());
        for (OAuth2AccessToken token : tokens) {
            // 删除刷新令牌
            if (token.getRefreshToken() != null) {
                tokenStore.removeRefreshToken(token.getRefreshToken());
            }
            // 删除令牌
            tokenStore.removeAccessToken(token);
        }

        UserDetails userDetails = customUserDetailService.createDetailByUser(user, client.getClientId());
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        OAuth2Authentication authentication = new OAuth2Authentication(getAccountOauth2Request(client), authenticationToken);
        // 生成令牌信息
        OAuth2AccessToken accessToken = authorizationServerTokenServices.createAccessToken(authentication);

//        tokenStore.storeAccessToken(accessToken, authentication);
        return accessToken;
    }

    /**
     * 退出登录
     *
     * @return
     */
    @Override
    public R logout() {
        UserInfoVo userInfo = UserUtil.getUserInfo();
        // 检测清理以前的令牌信息
        Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientIdAndUserName("admin", userInfo.getUsername());
        for (OAuth2AccessToken token : tokens) {
            // 删除刷新令牌
            if (token.getRefreshToken() != null) {
                tokenStore.removeRefreshToken(token.getRefreshToken());
            }
            // 删除令牌
            tokenStore.removeAccessToken(token);
        }
        return R.ok();
    }
}