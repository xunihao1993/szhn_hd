package com.caitb.library_manager.consts;

/**
 * 功能描述: 全局公共常量
 * <p>
 * 作者: July
 * 日期: 2019-03-11 15:35
 */
public interface CommonConstants {


    /** 编码 */
    String UTF8 = "UTF-8";

    /** JSON 资源 */
    String CONTENT_TYPE = "application/json;charset=UTF-8";

    /** Redis 前缀 */
    String REDIS_PREFIX = "chsp_";

    /** 内部跳转 前缀 */
    String TARGET_PREFIX = "chsp://";

    /** 商品唯一编号前缀 */
    String SHOP_CODE_PREFIX = "CH";

    /** 默认父类ID */
    Long DEFAULT_PARENT_ID = 0L;

    /** 默认排序 */
    Integer DEFAULT_SORT_VALUE = 100;

    /** 金额字段默认精度和小数点位数 */
    int DEFAULT_NUMBER_PRECISION = 18;
    int DEFAULT_NUMBER_SCALE = 8;
    int NUMBER_SCALE_TWO = 2;
    int NUMBER_SCALE_FOUR = 4;

    String AUTH_CLIENT_ID = "admin";

    // 书籍借出
    String BOOK_OPERATE_LOAN_OUT = "LOAN_OUT";
    // 书籍归还
    String BOOK_OPERATE_LOAN_IN = "LOAN_IN";

}
