package com.caitb.library_manager.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author
 */
@ApiModel("角色参数")
@Data
public class SysRoleAddParam {

    /**
     * 角色id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 角色名称
     */
    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("简介")
    private String descInfo;

    /**
     * 角色code
     */
    @ApiModelProperty("角色code")
    private String code;

    /**
     * 菜单id集合
     */
    @ApiModelProperty("菜单id集合")
    private List<Long> menuIds;

}
