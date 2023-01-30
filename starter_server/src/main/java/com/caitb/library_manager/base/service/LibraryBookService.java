package com.caitb.library_manager.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caitb.library_manager.base.domain.LibraryBook;
import com.caitb.library_manager.params.LibraryBookAddParam;
import com.caitb.library_manager.params.LibraryBookListParam;
import com.caitb.library_manager.params.LibraryBookLogParam;
import com.caitb.library_manager.params.LibraryBookOperateParam;
import com.caitb.library_manager.vo.LibraryBookBorrowLogVo;
import com.caitb.library_manager.vo.UserInfoVo;

/**
 *
 */
public interface LibraryBookService extends IService<LibraryBook> {

    /**
     * 根据code得到书籍信息
     * @param code
     * @return
     */
    LibraryBook getByCode(String code);

    /**
     * 新增图书信息
     * @param param
     */
    void addOrUpdate(LibraryBookAddParam param);

    /**
     * 删除书籍
     * @param id
     */
    void deleteBook(Long id);

    /**
     * 得到列表
     * @param param
     * @return
     */
    IPage<LibraryBook> getPage(LibraryBookListParam param);

    /**
     * 得到书籍详情
     * @param id
     * @return
     */
    LibraryBook getDetail(Long id);

    /**
     * 得到借阅日志
     * @param param
     * @return
     */
    IPage<LibraryBookBorrowLogVo> getLoanLogs(LibraryBookLogParam param);

    /**
     * 借出/归还书籍
     * @param userInfoVo
     * @param param
     */
    void loanOutInBook(UserInfoVo userInfoVo, LibraryBookOperateParam param);
}
