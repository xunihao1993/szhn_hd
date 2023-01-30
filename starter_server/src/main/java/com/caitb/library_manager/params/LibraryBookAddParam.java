package com.caitb.library_manager.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * @author
 */
@ApiModel("图书新增")
@Data
public class LibraryBookAddParam {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 图书名称
     */
    @NotBlank(message = "请输入图书名称")
    @ApiModelProperty("图书名称")
    private String bookName;

    /**
     * 简介
     */
    @ApiModelProperty("简介")
    private String descInfo;

    /**
     * 价格
     */
    @ApiModelProperty("价格")
    private BigDecimal price;

    /**
     * 分类名称
     */
    @NotBlank(message = "请输入分类名称")
    @ApiModelProperty("分类名称")
    private String bookType;

    @NotBlank(message = "请输入作者名称")
    @ApiModelProperty("作者")
    private String author;

}
