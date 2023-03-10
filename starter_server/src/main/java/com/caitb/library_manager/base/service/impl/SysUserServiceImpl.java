package com.caitb.library_manager.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caitb.library_manager.base.domain.SysMenu;
import com.caitb.library_manager.base.domain.SysRole;
import com.caitb.library_manager.base.domain.SysUser;
import com.caitb.library_manager.base.domain.SysUserRole;
import com.caitb.library_manager.base.mapper.SysUserMapper;
import com.caitb.library_manager.base.mapper.SysUserRoleMapper;
import com.caitb.library_manager.base.service.SysRoleService;
import com.caitb.library_manager.base.service.SysUserService;
import com.caitb.library_manager.dto.UserDetailVo;
import com.caitb.library_manager.exception.BusinessException;
import com.caitb.library_manager.params.BasePageParam;
import com.caitb.library_manager.params.SysUserAddParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleService sysRoleService;

    @Override
    @Transactional(readOnly = true)
    public SysUser queryOneByAccount(String username) {
        SysUser one = lambdaQuery()
                .eq(SysUser::getUsername, username)
                .one();

        return one;
    }

    @Override
    public UserDetailVo queryDetailVoById(Long id) {
        UserDetailVo res = new UserDetailVo();
        SysUser byId = getById(id);
        res.setId(byId.getId());
        res.setAvatar(byId.getAvatar());
        res.setMobile(byId.getMobile());
        res.setNickName(byId.getNickName());
        return res;
    }

    @Override
    public boolean isExistUsername(String username) {
        SysUser sysUser = this.queryOneByAccount(username);
        return sysUser != null;
    }

    /**
     * ?????????????????????code
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysRole> getUserRole(Long userId) {

        List<SysRole> userRole = this.baseMapper.getUserRole(userId);
        return userRole;
    }

    /**
     * ?????????????????????code
     *
     * @param roleIds
     * @return
     */
    @Override
    public List<SysMenu> getUserPermission(List<Long> roleIds) {
        List<SysMenu> userPermission = this.baseMapper.getUserPermission(roleIds);
        return userPermission;
    }

    /**
     * ??????code???????????????????????????
     *
     * @param username
     * @return
     */
    @Override
    public SysUser getByAccount(String username) {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        return this.lambdaQuery()
                .eq(SysUser::getUsername, username)
                .eq(SysUser::getIsDelete, 0)
                .one();
    }

    /**
     * ?????????????????????
     *
     * @param id
     */
    @Override
    public int deleteMiddleData(Long id) {

        // delete from sys_user_role where user_id = 1
        int delete = sysUserRoleMapper.delete(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, id));
        return delete;
    }

    /**
     * ????????????????????????
     *
     * @param param
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addOrUpdateUser(SysUserAddParam param) {

        boolean saveFlag = param.getId() == null;
        SysUser sysUser;

        if (saveFlag) {

            SysUser byCode = getByAccount(param.getUsername());
            if (byCode != null) {
                throw new BusinessException("???????????????????????????????????????");
            }
            sysUser = new SysUser();
        } else {
            sysUser = getById(param.getId());
            if (sysUser == null) {
                log.error("?????????????????????,param:{}", param);
                throw new BusinessException("?????????????????????");
            }

            if (!Objects.equals(sysUser.getUsername(), param.getUsername())) {
                SysUser byCode = getByAccount(param.getUsername());
                if (byCode != null) {
                    throw new BusinessException("???????????????????????????????????????");
                }
            }
            // ?????????????????????
            deleteMiddleData(param.getId());
        }

        // ????????????
        sysUser.setUsername(param.getUsername());
        sysUser.setNickName(param.getNickName());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        sysUser.setPassword(passwordEncoder.encode(param.getPassword()));

        this.saveOrUpdate(sysUser);

        // ?????????????????????
        List<Long> roleIds = param.getRoleIds();
        if (!CollectionUtils.isEmpty(roleIds)) {

            List<SysRole> roleList = sysRoleService.lambdaQuery()
                    .in(SysRole::getId, roleIds)
                    .list();
            if (!CollectionUtils.isEmpty(roleList)) {
                roleList.forEach(item -> {
                    SysUserRole sysRoleMenu = new SysUserRole();
                    sysRoleMenu.setRoleId(item.getId());
                    sysRoleMenu.setUserId(sysUser.getId());
                    int insert = sysUserRoleMapper.insert(sysRoleMenu);
                    if (insert != 1) {
                        log.error("?????????????????????:param:{}", sysRoleMenu);
                        throw new BusinessException("??????????????????");
                    }
                });
            }
        }
    }

    /**
     * ????????????
     *
     * @param param
     * @return
     */
    @Override
    public IPage<SysUser> page(BasePageParam param) {

        Page<SysUser> page = this.lambdaQuery().eq(SysUser::getIsDelete, 0)
        .orderByDesc(SysUser::getId)
        .page(new Page<>(param.getCurrent(), param.getPageSize()));

        // ????????????
        page.getRecords().forEach(item -> {
            List<SysRole> userRole = getUserRole(item.getId());
            if (!CollectionUtils.isEmpty(userRole)) {
                item.getRoleList().addAll(userRole.stream().map(SysRole::getName).collect(Collectors.toList()));
            }

            item.setPassword(null);
        });


        return page;
    }

    /**
     * ????????????
     *
     * @param id
     */
    @Override
    public void deleteUser(Long id) {

        SysUser byId = getById(id);
        if (byId == null) {
            return;
        }

        if ("admin".equalsIgnoreCase(byId.getUsername())) {
            throw new BusinessException("?????????????????????????????????");
        }

        boolean removeById = this.removeById(id);
        if (!removeById) {
            throw new BusinessException("?????????????????????");
        }

    }

    /**
     * ????????????id????????????map??????
     *
     * @param userIds
     * @return
     */
    @Override
    public Map<Long, SysUser> getUserMapByIds(List<Long> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            return new HashMap<>();
        }
        List<SysUser> list = this.lambdaQuery().eq(SysUser::getIsDelete, 0)
                .in(SysUser::getId, userIds)
                .list();
        Map<Long, SysUser> res = list.stream().collect(Collectors.toMap(SysUser::getId, item -> item));
        return res;
    }

}




