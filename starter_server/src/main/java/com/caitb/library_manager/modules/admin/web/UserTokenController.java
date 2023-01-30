package com.caitb.library_manager.modules.admin.web;

import com.caitb.library_manager.dto.R;
import com.caitb.library_manager.modules.admin.service.IUserTokenService;
import com.caitb.library_manager.params.LoginParam;
import com.caitb.library_manager.utils.UserUtil;
import com.caitb.library_manager.vo.UserInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能描述: 登录认证接口
 * <p>
 * 作者: July
 * 日期: 2019-04-10 17:18
 */
@Slf4j
@Api(value = "token管理", tags = "token管理")
@RestController
@RequestMapping("/oauth")
public class UserTokenController {

    @Autowired
    private IUserTokenService userTokenService;

    /**
     * 功能描述: 用户登录接口
     * <p>
     * 作者: July
     * 日期: 2020-04-22 16:37:52
     */
    @ApiOperation("登录接口")
    @PostMapping("/login")
    public R login(@RequestBody @Validated LoginParam param, HttpServletRequest request) {
        return userTokenService.login(param, request);
    }

    /**
     * 功能描述: 其他微服务获取认证信息
     * <p>
     * 作者: July
     * 日期: 2018-01-08 09:50:18
     */
    @ApiOperation("得到用户信息接口")
    @GetMapping("/user/info")
    public R<UserInfoVo> getCurrentLoggedInUser() {
        UserInfoVo result = UserUtil.getUserInfo();
        return R.ok(result);
    }

    /**
     * 退出登录接口
     * @return
     */
    @ApiOperation("退出登录接口")
    @PostMapping("/logout")
    public R logout() {
        UserInfoVo userInfo = UserUtil.getUserInfo();
        try {
            R logout = userTokenService.logout();
            return logout;
        } catch (Exception e) {
            log.error("UserTokenController:logout:param:{}:message:{}", userInfo, e.getMessage());
            return R.error("退出登录失败");
        }
    }

}