package com.caitb.library_manager.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caitb.library_manager.base.domain.SysMenu;
import com.caitb.library_manager.base.domain.SysRole;
import com.caitb.library_manager.base.domain.SysUser;
import com.caitb.library_manager.dto.UserDetailVo;
import com.caitb.library_manager.params.BasePageParam;
import com.caitb.library_manager.params.SysUserAddParam;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface SysUserService extends IService<SysUser> {

    SysUser queryOneByAccount(String username);

    UserDetailVo queryDetailVoById(Long id);

    boolean isExistUsername(String username);

    /**
     * 得到用户的用户code
     * @param userId
     * @return
     */
    List<SysRole> getUserRole(Long userId);

    /**
     * 得到用户的菜单code
     * @param roleIds
     * @return
     */
    List<SysMenu> getUserPermission(List<Long> roleIds);


    /**
     * 通过code得到对应的用户信息
     * @param username
     * @return
     */
    SysUser getByAccount(String username);

    /**
     * 删除中间表数据
     * @param id
     */
    int deleteMiddleData(Long id);

    /**
     * 新增修改一个用户
     * @param param
     */
    void addOrUpdateUser(SysUserAddParam param);

    /**
     * 分页参数
     * @return
     */
    IPage<SysUser> page(BasePageParam param);

    /**
     * 删除菜单
     * @param id
     */
    void deleteUser(Long id);

    /**
     * 根据用户id得到用户map集合
     * @param userIds
     * @return
     */
    Map<Long, SysUser> getUserMapByIds(List<Long> userIds);
}
