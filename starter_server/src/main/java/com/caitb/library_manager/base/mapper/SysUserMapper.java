package com.caitb.library_manager.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caitb.library_manager.base.domain.SysMenu;
import com.caitb.library_manager.base.domain.SysRole;
import com.caitb.library_manager.base.domain.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Entity com.caitb.library_manager.base.domain.SysUser
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 得到用户的角色code
     */
    @Select("SELECT sys_role.`code`, sys_role.id, sys_role.name FROM sys_user_role \n" +
            "LEFT JOIN sys_user \n" +
            "ON sys_user_role.user_id = sys_user.id\n" +
            "LEFT JOIN sys_role\n" +
            "ON sys_user_role.role_id = sys_role.id\n" +
            "WHERE sys_user.is_delete = 0 AND sys_role.is_delete = 0 AND sys_user.id = #{userId} GROUP BY sys_role.code")
    List<SysRole> getUserRole(@Param("userId") Long userId);

    /**
     * 得到用户的权限code
     * @param roleIds
     * @return
     */
    List<SysMenu> getUserPermission(@Param("roleIds") List<Long> roleIds);

}




