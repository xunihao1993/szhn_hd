package com.caitb.library_manager.modules.admin.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.caitb.library_manager.base.domain.SysRole;
import com.caitb.library_manager.base.service.SysRoleService;
import com.caitb.library_manager.dto.R;
import com.caitb.library_manager.exception.BusinessException;
import com.caitb.library_manager.params.BaseDeleteParam;
import com.caitb.library_manager.params.BasePageParam;
import com.caitb.library_manager.params.SysRoleAddParam;
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
@RequestMapping("/sysRole")
@Api(value = "角色控制器", tags = "角色控制器")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 新增/修改
     * @param param
     * @return
     */
    @PreAuthorize("hasRole('admin')")
    @ApiOperation("新增一个角色")
    @PostMapping("/add")
    public R add(@RequestBody @Validated SysRoleAddParam param) {

        try {
            sysRoleService.addOrUpdateMenu(param);
            return CommonUtils.generateOk();
        } catch (BusinessException e) {
            return CommonUtils.generateError(R.error(e.getMessage()));
        } catch (Exception e) {
            log.error("SysMenuController:add:param:{}:message:{}", param, e.getMessage());
            return CommonUtils.generateError(R.error(e.getMessage()));
        }
    }

    /**
     * 查询角色
     * @param param
     * @return
     */
    @PreAuthorize("hasRole('admin')")
    @ApiOperation("查询角色")
    @PostMapping("/getPage")
    public R<IPage<SysRole>> getPage(@RequestBody BasePageParam param) {
        try {
            IPage<SysRole> page = sysRoleService.page(param);
            return R.ok(page);
        } catch (BusinessException e) {
            return CommonUtils.generateError(R.error(e.getMessage()));
        } catch (Exception e) {
            log.error("SysMenuController:getPage:param:{}:message:{}", param, e.getMessage());
            return CommonUtils.generateError(R.error(e.getMessage()));
        }
    }

    /**
     * 删除角色
     * @param param
     * @return
     */
    @PreAuthorize("hasRole('admin')")
    @ApiOperation("删除角色")
    @PostMapping("/deleteById")
    public R deleteById(@RequestBody BaseDeleteParam param) {
        try {
            sysRoleService.deleteRole(param.getId());
            return CommonUtils.generateOk();
        } catch (BusinessException e) {
            return CommonUtils.generateError(R.error(e.getMessage()));
        } catch (Exception e) {
            log.error("SysMenuController:deleteById:param:{}:message:{}", param, e.getMessage());
            return CommonUtils.generateError(R.error(e.getMessage()));
        }
    }

}
