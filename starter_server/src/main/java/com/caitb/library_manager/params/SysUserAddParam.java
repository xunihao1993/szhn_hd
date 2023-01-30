package com.caitb.library_manager.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author
 */
@ApiModel("用户参数")
@Data
public class SysUserAddParam {

    /**
     * 用户id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 用户账号
     */
    @ApiModelProperty("账号名称")
    private String username;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickName;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 角色id集合
     */
    @ApiModelProperty("角色id集合")
    private List<Long> roleIds;

}
