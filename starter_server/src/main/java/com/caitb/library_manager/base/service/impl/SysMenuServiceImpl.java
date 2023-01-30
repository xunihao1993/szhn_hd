package com.caitb.library_manager.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caitb.library_manager.base.domain.SysMenu;
import com.caitb.library_manager.base.mapper.SysMenuMapper;
import com.caitb.library_manager.base.service.SysMenuService;
import com.caitb.library_manager.exception.BusinessException;
import com.caitb.library_manager.params.BasePageParam;
import com.caitb.library_manager.params.SysMenuAddParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 *
 */
@Slf4j
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
    implements SysMenuService{

    /**
     * 通过code得到对应的菜单信息
     *
     * @param code
     * @return
     */
    @Override
    public SysMenu getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        return this.lambdaQuery()
                .eq(SysMenu::getCode, code)
                .eq(SysMenu::getIsDelete, 0)
                .one();
    }

    /**
     * 新增修改一个菜单
     *
     * @param param
     */
    @Override
    public void addOrUpdateMenu(SysMenuAddParam param) {

        boolean saveFlag = param.getId() == null;
        SysMenu sysMenu;
        if (saveFlag) {
            SysMenu byCode = getByCode(param.getCode());
            if (byCode != null) {
                throw new BusinessException("该菜单已存在，请勿重新添加");
            }
            sysMenu = new SysMenu();
        } else {

            sysMenu = getById(param.getId());

            if (sysMenu == null) {
                log.error("该菜单不存在:param:{}", param);
                throw new BusinessException("很抱歉，该菜单不存在");
            }
            if (!Objects.equals(sysMenu.getCode(), param.getCode())) {
                SysMenu byCode = getByCode(param.getCode());
                if (byCode != null) {
                    throw new BusinessException("该菜单已存在，请勿重新添加");
                }
            }
        }

        sysMenu.setCode(param.getCode());
        sysMenu.setName(param.getName());
        sysMenu.setIsDelete(0);
        sysMenu.setShowFlag(param.isShowFlag() ? 0 : 1);
        sysMenu.setType(param.isShowFlag() ? 0 : 1);
        boolean saveOrUpdate = this.saveOrUpdate(sysMenu);
        if (!saveOrUpdate) {
            log.error(saveFlag ? "保存菜单失败:param:{}" : "修改菜单失败:param{}", param);
            throw new BusinessException(saveFlag ? "保存菜单失败" : "修改菜单失败");
        }

    }

    /**
     * 分页参数
     *
     * @param param
     * @return
     */
    @Override
    public IPage<SysMenu> page(BasePageParam param) {
        Page<SysMenu> page = this.lambdaQuery()
                .eq(SysMenu::getIsDelete, 0)
                .orderByDesc(SysMenu::getId)
                .page(new Page<>(param.getCurrent(), param.getPageSize()));
        return page;
    }

    /**
     * 删除菜单
     *
     * @param id
     */
    @Override
    public void deleteMenu(Long id) {
        boolean removeById = this.removeById(id);
        if (!removeById) {
            log.error("删除失败:param:{}", id);
            throw new BusinessException("删除失败");
        }
    }
}




