package com.caitb.library_manager.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caitb.library_manager.base.domain.LibraryBookBorrowLog;
import com.caitb.library_manager.vo.LibraryBookBorrowLogVo;
import org.apache.ibatis.annotations.Param;

/**
 * @Entity com.caitb.library_manager.base.domain.LibraryBookBorrowLog
 */
public interface LibraryBookBorrowLogMapper extends BaseMapper<LibraryBookBorrowLog> {

    /**
     * 得到借阅日志
     * @param id
     * @return
     */
    IPage<LibraryBookBorrowLogVo> getBorrowLogs(Page<LibraryBookBorrowLog> page, @Param("id")Long id);

}




