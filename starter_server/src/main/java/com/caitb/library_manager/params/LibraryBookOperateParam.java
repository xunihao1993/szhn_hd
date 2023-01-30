package com.caitb.library_manager.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author
 */
@ApiModel("书籍操作类")
@Data
public class LibraryBookOperateParam {

    @ApiModelProperty("操作类型：LOAN_OUT代表借出 LOAN_IN代表归还")
    private String operateFlag;

    @ApiModelProperty("书籍id")
    private Long bookId;

}
