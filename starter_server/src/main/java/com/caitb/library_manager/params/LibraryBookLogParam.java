package com.caitb.library_manager.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author
 */
@ApiModel("查询借阅日志对象")
@Data
@EqualsAndHashCode(callSuper = true)
public class LibraryBookLogParam extends BasePageParam{

    @NotNull(message = "请输入书籍id")
    @ApiModelProperty("书籍id")
    private Long id;
}
