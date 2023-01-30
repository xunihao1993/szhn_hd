package com.caitb.library_manager.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author
 */
@ApiModel("登录参数")
@Data
public class LoginParam {

    /**
     * 账号名
     */
    @ApiModelProperty("账户名")
    @NotBlank(message = "账号名")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 客户端id
     */
    @ApiModelProperty(value = "客户端id", hidden = true)
    private String clientId = "admin";

}
