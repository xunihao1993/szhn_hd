package com.caitb.library_manager.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询参数
 * @author
 */
@ApiModel("书籍列表参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class LibraryBookListParam extends BasePageParam {

    @ApiModelProperty("书籍名称")
    private String bookName;

    @ApiModelProperty("书籍编码")
    private String code;

    @ApiModelProperty("类型名称")
    private String bookType;

    @ApiModelProperty("作者名")
    private String author;

}
