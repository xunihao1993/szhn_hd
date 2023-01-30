package com.caitb.library_manager.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caitb.library_manager.base.domain.SysRole;
import com.caitb.library_manager.params.BasePageParam;
import com.caitb.library_manager.params.SysRoleAddParam;

/**
 *
 */
public interface SysRoleService extends IService<SysRole> {
    /**
     * 通过code得到对应的角色信息
     * @param code
     * @return
     */
    SysRole getByCode(String code);

    /**
     * 删除中间表数据
     * @param id
     */
    int deleteMiddleData(Long id);

    /**
     * 新增修改一个角色
     * @param param
     */
    void addOrUpdateMenu(SysRoleAddParam param);

    /**
     * 分页参数
     * @return
     */
    IPage<SysRole> page(BasePageParam param);

    /**
     * 删除菜单
     * @param id
     */
    void deleteRole(Long id);
}
