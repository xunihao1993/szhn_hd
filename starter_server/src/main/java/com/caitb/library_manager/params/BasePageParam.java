package com.caitb.library_manager.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author
 */
@ApiModel("基础分页参数")
@Data
public class BasePageParam {

    /**
     * 页数
     */
    @ApiModelProperty("页数")
    private int current = 1;

    /**
     * 条数
     */
    @ApiModelProperty("条数")
    private int pageSize = 10;

}
