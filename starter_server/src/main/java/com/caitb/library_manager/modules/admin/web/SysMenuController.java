package com.caitb.library_manager.modules.admin.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.caitb.library_manager.base.domain.SysMenu;
import com.caitb.library_manager.base.service.SysMenuService;
import com.caitb.library_manager.dto.R;
import com.caitb.library_manager.exception.BusinessException;
import com.caitb.library_manager.params.BaseDeleteParam;
import com.caitb.library_manager.params.BasePageParam;
import com.caitb.library_manager.params.SysMenuAddParam;
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
@RequestMapping("/sysMenu")
@Api(value = "菜单控制器", tags = "菜单控制器")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 新增/修改
     * @param param
     * @return
     */
    @PreAuthorize("hasRole('admin')")
    @ApiOperation("新增一个菜单")
    @PostMapping("/add")
    public R add(@RequestBody @Validated SysMenuAddParam param) {

        try {
            sysMenuService.addOrUpdateMenu(param);
            return CommonUtils.generateOk();
        } catch (BusinessException e) {
            return CommonUtils.generateError(R.error(e.getMessage()));
        } catch (Exception e) {
            log.error("SysMenuController:add:param:{}:message:{}", param, e.getMessage());
            return CommonUtils.generateError(R.error(e.getMessage()));
        }
    }

    /**
     * 查询菜单
     * @param param
     * @return
     */
    @PreAuthorize("hasRole('admin')")
    @ApiOperation("查询菜单")
    @PostMapping("/getPage")
    public R<IPage<SysMenu>> getPage(@RequestBody BasePageParam param) {
        try {
            IPage<SysMenu> page = sysMenuService.page(param);
            return R.ok(page);
        } catch (BusinessException e) {
            return CommonUtils.generateError(R.error(e.getMessage()));
        } catch (Exception e) {
            log.error("SysMenuController:getPage:param:{}:message:{}", param, e.getMessage());
            return CommonUtils.generateError(R.error(e.getMessage()));
        }
    }

    /**
     * 删除菜单
     * @param param
     * @return
     */
    @PreAuthorize("hasRole('admin')")
    @ApiOperation("删除菜单")
    @PostMapping("/deleteById")
    public R deleteById(@RequestBody BaseDeleteParam param) {
        try {
            sysMenuService.deleteMenu(param.getId());
            return CommonUtils.generateOk();
        } catch (BusinessException e) {
            return CommonUtils.generateError(R.error(e.getMessage()));
        } catch (Exception e) {
            log.error("SysMenuController:deleteById:param:{}:message:{}", param, e.getMessage());
            return CommonUtils.generateError(R.error(e.getMessage()));
        }
    }
}
