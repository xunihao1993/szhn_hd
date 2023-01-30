package com.caitb.library_manager.modules.admin.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.caitb.library_manager.base.domain.SysUser;
import com.caitb.library_manager.base.service.SysUserService;
import com.caitb.library_manager.dto.R;
import com.caitb.library_manager.exception.BusinessException;
import com.caitb.library_manager.params.BaseDeleteParam;
import com.caitb.library_manager.params.BasePageParam;
import com.caitb.library_manager.params.SysUserAddParam;
import com.caitb.library_manager.utils.CommonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 */
@Slf4j
@RestController
@RequestMapping("/sysUser")
@Api(value = "用户控制器", tags = "用户控制器")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 新增/修改
     * @param param
     * @return
     */
    @PreAuthorize("hasRole('admin')")
    @ApiOperation("新增一个用户")
    @PostMapping("/add")
    public R add(@RequestBody @Validated SysUserAddParam param) {

        try {
            sysUserService.addOrUpdateUser(param);
            return CommonUtils.generateOk();
        } catch (BusinessException e) {
            return CommonUtils.generateError(R.error(e.getMessage()));
        } catch (Exception e) {
            log.error("SysMenuController:add:param:{}:message:{}", param, e.getMessage());
            return CommonUtils.generateError(R.error(e.getMessage()));
        }
    }

    /**
     * 查询用户
     * @param param
     * @return
     */
    @PreAuthorize("hasRole('admin')")
    @ApiOperation("查询用户")
    @PostMapping("/getPage")
    public R<IPage<SysUser>> getPage(@RequestBody BasePageParam param) {
        try {
            IPage<SysUser> page = sysUserService.page(param);
            return R.ok(page);
        } catch (BusinessException e) {
            return CommonUtils.generateError(R.error(e.getMessage()));
        } catch (Exception e) {
            log.error("SysMenuController:getPage:param:{}:message:{}", param, e.getMessage());
            return CommonUtils.generateError(R.error(e.getMessage()));
        }
    }

    /**
     * 删除用户
     * @param param
     * @return
     */
    @PreAuthorize("hasRole('admin')")
    @ApiOperation("删除用户")
    @PostMapping("/deleteById")
    public R deleteById(@RequestBody BaseDeleteParam param) {
        try {
            sysUserService.deleteUser(param.getId());
            return CommonUtils.generateOk();
        } catch (BusinessException e) {
            return CommonUtils.generateError(R.error(e.getMessage()));
        } catch (Exception e) {
            log.error("SysMenuController:deleteById:param:{}:message:{}", param, e.getMessage());
            return CommonUtils.generateError(R.error(e.getMessage()));
        }
    }


}
