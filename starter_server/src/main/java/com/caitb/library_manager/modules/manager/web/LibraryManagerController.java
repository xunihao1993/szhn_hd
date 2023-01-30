package com.caitb.library_manager.modules.manager.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.caitb.library_manager.base.domain.LibraryBook;
import com.caitb.library_manager.base.service.LibraryBookService;
import com.caitb.library_manager.dto.R;
import com.caitb.library_manager.exception.BusinessException;
import com.caitb.library_manager.params.*;
import com.caitb.library_manager.utils.CommonUtils;
import com.caitb.library_manager.utils.UserUtil;
import com.caitb.library_manager.vo.LibraryBookBorrowLogVo;
import com.caitb.library_manager.vo.UserInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author
 */
@Api(value = "图书管理控制器", tags = "图书管理")
@Slf4j
@RequestMapping("/library")
@RestController
public class LibraryManagerController {

    @Autowired
    private LibraryBookService libraryBookService;

    @PreAuthorize("hasRole('admin') || hasAuthority('library:book')")
    @ApiOperation("测试")
    @GetMapping("/test")
    public R test() {
        return R.ok();
    }

    /**
     * 新增书籍
     * @param param
     * @return
     */
    @PreAuthorize("hasRole('admin') || hasAuthority('library:book:add')|| hasAuthority('library:book:edit')")
    @ApiOperation("新增书籍")
    @PostMapping("/addOrUpdate")
    public R addOrUpdate(@RequestBody @Validated LibraryBookAddParam param) {
        try {
            libraryBookService.addOrUpdate(param);
            return CommonUtils.generateOk();
        } catch (BusinessException e) {
            return CommonUtils.generateError(R.error(e.getMessage()));
        } catch (Exception e) {
            log.error("LibraryManagerController:addOrUpdate:param:{}:message:{}", param, e.getMessage());
            return CommonUtils.generateError(R.error("新增书籍失败"));
        }
    }

    /**
     * 删除书籍
     * @param param
     * @return
     */
    @PreAuthorize("hasRole('admin')")
    @ApiOperation("删除书籍")
    @PostMapping("/deleteBook")
    public R deleteBook(@RequestBody BaseDeleteParam param) {
        try {
            libraryBookService.deleteBook(param.getId());
            return CommonUtils.generateOk();
        } catch (BusinessException e) {
            return CommonUtils.generateError(R.error(e.getMessage()));
        } catch (Exception e) {
            log.error("LibraryManagerController:deleteBook:param:{}", param);
            return CommonUtils.generateError(R.error("删除书籍失败"));
        }
    }

    /**
     * 分页查看书籍
     * @param param
     * @return
     */
    @PreAuthorize("hasRole('admin') || hasAuthority('library:book:list')")
    @ApiOperation("分页查看书籍")
    @PostMapping("/getPage")
    public R<IPage<LibraryBook>> getPage(@RequestBody LibraryBookListParam param) {
        try {
            IPage<LibraryBook> page = libraryBookService.getPage(param);
            return R.ok(page);
        } catch (BusinessException e) {
            return CommonUtils.generateError(R.error(e.getMessage()));
        } catch (Exception e) {
            log.error("LibraryManagerController:getPage:param:{}", param);
            return CommonUtils.generateError(R.error("分页查看书籍失败"));
        }
    }

    /**
     *  查看详情
     * @param id
     * @return
     */
    @PreAuthorize("hasRole('admin')|| hasAuthority('library:book:list')")
    @ApiOperation("查看书籍详情")
    @GetMapping("/getDetail")
    public R<LibraryBook> getDetail(@RequestParam("id")
                                   Long id) {
        try {
            LibraryBook detail = libraryBookService.getDetail(id);
            return R.ok(detail);
        } catch (BusinessException e) {
            return CommonUtils.generateError(R.error(e.getMessage()));
        } catch (Exception e) {
            log.error("LibraryManagerController:getDetail:param:{}", id);
            return CommonUtils.generateError(R.error("查看书籍详情失败"));
        }
    }

    /**
     * 查看借阅日志
     * @param id
     * @return
     */
    @PreAuthorize("hasRole('admin')|| hasAuthority('library:book:list')")
    @ApiOperation("查看书籍借阅日志")
    @PostMapping("/getLoanLogs")
    public R<IPage<LibraryBookBorrowLogVo>> getLoanLogs(@RequestBody @Validated LibraryBookLogParam param) {
        try {
            IPage<LibraryBookBorrowLogVo> bookBorrowLogVos = libraryBookService.getLoanLogs(param);
            return R.ok(bookBorrowLogVos);
        } catch (BusinessException e) {
            return CommonUtils.generateError(R.error(e.getMessage()));
        } catch (Exception e) {
            log.error("LibraryManagerController:getLoanLogs:param:{}", param);
            return CommonUtils.generateError(R.error("查看书籍借阅日志失败"));
        }
    }

    // 借出/归还书籍
    /**
     * 借出/归还书籍
     * @param param
     * @return
     */
    @PreAuthorize("hasRole('admin')|| hasAuthority('library:book:list')")
    @ApiOperation("借出/归还书籍")
    @PostMapping("/loanOutInBook")
    public R loanOutInBook(@RequestBody LibraryBookOperateParam param) {
        try {
            UserInfoVo userInfo = UserUtil.getUserInfo();
            libraryBookService.loanOutInBook(userInfo, param);
            return CommonUtils.generateOk();
        } catch (BusinessException e) {
            return CommonUtils.generateError(R.error(e.getMessage()));
        } catch (Exception e) {
            log.error("LibraryManagerController:loanOutInBook:param:{}:message:{}", param, e.getMessage());
            return CommonUtils.generateError(R.error("借出/归还书籍失败"));
        }
    }

}
