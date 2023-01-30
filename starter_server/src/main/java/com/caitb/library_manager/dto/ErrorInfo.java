package com.caitb.library_manager.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author
 */
@Data
@Accessors(chain = true)
public class ErrorInfo {

    /**
     * 错误码
     */
    private String errCode;

    /**
     * 错误描述
     */
    private String errDec;

}
