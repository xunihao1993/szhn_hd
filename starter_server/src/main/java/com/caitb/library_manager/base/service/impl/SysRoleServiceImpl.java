package com.caitb.library_manager.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caitb.library_manager.base.domain.SysMenu;
import com.caitb.library_manager.base.domain.SysRole;
import com.caitb.library_manager.base.domain.SysRoleMenu;
import com.caitb.library_manager.base.mapper.SysRoleMenuMapper;
import com.caitb.library_manager.base.service.SysMenuService;
import com.caitb.library_manager.base.service.SysRoleService;
import com.caitb.library_manager.base.mapper.SysRoleMapper;
import com.caitb.library_manager.exception.BusinessException;
import com.caitb.library_manager.params.BasePageParam;
import com.caitb.library_manager.params.SysRoleAddParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 角色服务类
 */
@Slf4j
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
    implements SysRoleService{

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 通过code得到对应的角色信息
     *
     * @param code
     * @return
     */
    @Override
    public SysRole getByCode(String code) {
        return this.lambdaQuery().eq(SysRole::getCode, code)
                .eq(SysRole::getIsDelete, 0)
                .one();
    }

    /**
     * 删除中间表数据
     *
     * @param id
     */
    @Override
    public int deleteMiddleData(Long id) {
        int delete = sysRoleMenuMapper.delete(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, id));
        return delete;
    }

    /**
     * 新增修改一个角色
     *
     * @param param
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addOrUpdateMenu(SysRoleAddParam param) {

        boolean saveFlag = param.getId() == null;
        SysRole sysRole;

        if (saveFlag) {

            SysRole byCode = getByCode(param.getCode());
            if (byCode != null) {
                throw new BusinessException("该角色已存在，请勿重新添加");
            }
            sysRole = new SysRole();
        } else {
            sysRole = getById(param.getId());
            if (sysRole == null) {
                log.error("当前角色不存在,param:{}", param);
                throw new BusinessException("当前角色不存在");
            }

            if (!Objects.equals(sysRole.getCode(), param.getCode())) {
                SysRole byCode = getByCode(param.getCode());
                if (byCode != null) {
                    throw new BusinessException("该角色已存在，请勿重新添加");
                }
            }
            // 删除中间表数据
            deleteMiddleData(param.getId());
        }

        // 保存数据
        sysRole.setCode(param.getCode());
        sysRole.setDescInfo(param.getDescInfo());
        sysRole.setName(param.getName());
        this.saveOrUpdate(sysRole);

        // 保存中间表数据
        List<Long> menuIds = param.getMenuIds();
        if (!CollectionUtils.isEmpty(menuIds)) {

            List<SysMenu> menuList = sysMenuService.lambdaQuery()
                    .in(SysMenu::getId, menuIds)
                    .list();
            if (!CollectionUtils.isEmpty(menuList)) {
                menuList.forEach(item -> {
                    SysRoleMenu sysRoleMenu = new SysRoleMenu();
                    sysRoleMenu.setMenuId(item.getId());
                    sysRoleMenu.setRoleId(sysRole.getId());
                    int insert = sysRoleMenuMapper.insert(sysRoleMenu);
                    if (insert != 1) {
                        log.error("保存中间表失败:param:{}", sysRoleMenu);
                        throw new BusinessException("保存角色失败");
                    }
                });
            }
        }
    }

    /**
     * 分页参数
     *
     * @param param
     * @return
     */
    @Override
    public IPage<SysRole> page(BasePageParam param) {
        Page<SysRole> page = this.lambdaQuery()
                .eq(SysRole::getIsDelete, 0)
                .orderByDesc(SysRole::getId)
                .page(new Page<>(param.getCurrent(), param.getPageSize()));

        return page;
    }

    /**
     * 删除菜单
     *
     * @param id
     */
    @Override
    public void deleteRole(Long id) {
        boolean removeById = this.removeById(id);
        if (!removeById) {
            throw new BusinessException("删除失败");
        }
    }
}




