package com.caitb.library_manager.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author
 */
@ApiModel("菜单参数")
@Data
public class SysMenuAddParam {

    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    @NotBlank(message = "请填写菜单名称")
    private String name;

    /**
     * 菜单路径
     */
    @ApiModelProperty("路径")
    private String path;

    /**
     * 菜单code
     */
    @ApiModelProperty("菜单code")
    @NotBlank(message = "请填写菜单code")
    private String code;

    /**
     * 是否显示
     */
    @ApiModelProperty("是否显示，默认显示")
    private boolean showFlag = true;

}
