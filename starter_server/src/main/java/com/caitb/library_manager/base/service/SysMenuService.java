package com.caitb.library_manager.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.caitb.library_manager.base.domain.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caitb.library_manager.params.BasePageParam;
import com.caitb.library_manager.params.SysMenuAddParam;

/**
 *
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 通过code得到对应的菜单信息
     * @param code
     * @return
     */
    SysMenu getByCode(String code);

    /**
     * 新增修改一个菜单
     * @param param
     */
    void addOrUpdateMenu(SysMenuAddParam param);

    /**
     * 分页参数
     * @return
     */
    IPage<SysMenu> page(BasePageParam param);

    /**
     * 删除菜单
     * @param id
     */
    void deleteMenu(Long id);

}
