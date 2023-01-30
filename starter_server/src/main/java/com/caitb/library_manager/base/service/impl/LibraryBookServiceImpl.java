package com.caitb.library_manager.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caitb.library_manager.base.domain.LibraryBook;
import com.caitb.library_manager.base.domain.LibraryBookBorrowLog;
import com.caitb.library_manager.base.domain.SysUser;
import com.caitb.library_manager.base.mapper.LibraryBookBorrowLogMapper;
import com.caitb.library_manager.base.mapper.LibraryBookMapper;
import com.caitb.library_manager.base.service.LibraryBookService;
import com.caitb.library_manager.base.service.SysUserService;
import com.caitb.library_manager.consts.CommonConstants;
import com.caitb.library_manager.exception.BusinessException;
import com.caitb.library_manager.params.LibraryBookAddParam;
import com.caitb.library_manager.params.LibraryBookListParam;
import com.caitb.library_manager.params.LibraryBookLogParam;
import com.caitb.library_manager.params.LibraryBookOperateParam;
import com.caitb.library_manager.utils.CommonUtils;
import com.caitb.library_manager.vo.LibraryBookBorrowLogVo;
import com.caitb.library_manager.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 */
@Slf4j
@Service
public class LibraryBookServiceImpl extends ServiceImpl<LibraryBookMapper, LibraryBook>
    implements LibraryBookService{

    @Resource
    private LibraryBookBorrowLogMapper libraryBookBorrowLogMapper;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 根据code得到书籍信息
     *
     * @param code
     * @return
     */
    @Override
    public LibraryBook getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        return this.lambdaQuery()
                .eq(LibraryBook::getCode, code)
                .eq(LibraryBook::getIsDelete, 0)
                .one();
    }

    /**
     * 新增图书信息
     *
     * @param param
     */
    @Override
    public void addOrUpdate(LibraryBookAddParam param) {

        boolean saveFlag = param.getId() == null;
        LibraryBook book;
        if (saveFlag) {
            book = new LibraryBook();
            book.setStock(1);
            // 设置书籍信息
            book.setCode(CommonUtils.generateCode("BOOK_"));
        } else {
            book = getById(param.getId());

            if (book == null) {
                log.error("该书籍不存在:param:{}", param);
                throw new BusinessException("很抱歉，该书籍不存在");
            }
        }

        book.setBookName(param.getBookName());
        book.setDescInfo(param.getDescInfo());
        book.setPrice(param.getPrice());
        book.setBookType(param.getBookType());
        book.setVersion(saveFlag ? 0 : book.getVersion());
        book.setAuthor(param.getAuthor());

        if (saveFlag) {
            boolean save = this.save(book);
            if (!save) {
                log.error("LibraryBookServiceImpl:addOrUpdate:param:{}:message:{}", param, "保存书籍信息失败");
                throw new BusinessException("保存书籍失败");
            }
        } else {
            boolean saveOrUpdate = this.saveOrUpdate(book, new LambdaQueryWrapper<LibraryBook>()
                    .eq(LibraryBook::getId, book.getId())
                    .eq(LibraryBook::getVersion, book.getVersion()));
            if (!saveOrUpdate) {
                log.error("LibraryBookServiceImpl:addOrUpdate:param:{}:message:{}", param, "保存书籍信息失败");
                throw new BusinessException("保存书籍失败");
            }
        }
    }

    /**
     * 删除书籍
     *
     * @param id
     */
    @Override
    public void deleteBook(Long id) {
        // 在这里进行软删除
        boolean update = this.lambdaUpdate().set(LibraryBook::getIsDelete, 1)
                .eq(LibraryBook::getIsDelete, 0)
                .eq(LibraryBook::getId, id)
                .update();
        if (!update) {
            log.error("LibraryBookServiceImpl:deleteBook:param:{}:message:{}", id, "删除书籍信息失败");
            throw new BusinessException("删除书籍信息失败");
        }
    }

    /**
     * 得到列表
     *
     * @param param
     * @return
     */
    @Override
    public IPage<LibraryBook> getPage(LibraryBookListParam param) {
        Page<LibraryBook> page = this.lambdaQuery()
                .eq(LibraryBook::getIsDelete, 0)
                .eq(StringUtils.isNotBlank(param.getCode()), LibraryBook::getCode, param.getCode())
                .eq(StringUtils.isNotBlank(param.getBookType()) && !"all".equalsIgnoreCase(param.getBookType()), LibraryBook::getBookType, param.getBookType())
                .like(StringUtils.isNotBlank(param.getBookName()), LibraryBook::getBookName, param.getBookName())
                .like(StringUtils.isNotBlank(param.getAuthor()), LibraryBook::getAuthor, param.getAuthor())
                .orderByDesc(LibraryBook::getId)
                .page(new Page<>(param.getCurrent(), param.getPageSize()));

        List<Long> userIds = page.getRecords().stream().map(LibraryBook::getLoanOutUserId).filter(Objects::nonNull).collect(Collectors.toList());
        Map<Long, SysUser> userMap = sysUserService.getUserMapByIds(userIds);
        page.getRecords().forEach(item -> {
            if (item.getLoanOutUserId() != null && userMap.containsKey(item.getLoanOutUserId())) {
                item.setLoanOutUserName(userMap.get(item.getLoanOutUserId()).getUsername());
            }
        });
        return page;
    }

    /**
     * 得到书籍详情
     *
     * @param id
     * @return
     */
    @Override
    public LibraryBook getDetail(Long id) {
        LibraryBook byId = this.getById(id);
        return (byId == null || byId.getIsDelete() == 1) ? null : byId;
    }

    /**
     * 得到借阅日志
     *
     * @param param
     * @return
     */
    @Override
    public IPage<LibraryBookBorrowLogVo> getLoanLogs(LibraryBookLogParam param) {

        IPage<LibraryBookBorrowLogVo> borrowLogs = libraryBookBorrowLogMapper.getBorrowLogs(
                new Page<>(param.getCurrent(), param.getPageSize()), param.getId());
        return borrowLogs;
    }

    /**
     * 借出/归还书籍
     *
     * @param userInfoVo
     * @param param
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void loanOutInBook(UserInfoVo userInfoVo, LibraryBookOperateParam param) {

        // 得到图书信息
        LibraryBook byId = this.getById(param.getBookId());
        if (byId == null || byId.getIsDelete() == 1) {
            throw new BusinessException("该书籍信息不存在");
        }

        // 修改书籍库存信息
        if (CommonConstants.BOOK_OPERATE_LOAN_OUT.equalsIgnoreCase(param.getOperateFlag())) {

            if (byId.getState() != 0) {
                throw new BusinessException("该书籍已经被借阅出去了，不能重复借阅");
            }

            int i = this.baseMapper.minusStock(byId.getId(), userInfoVo.getId(), byId.getVersion());
            if (i != 1) {
                log.error("删减库存失败");
                throw new BusinessException(CommonConstants.BOOK_OPERATE_LOAN_OUT.equalsIgnoreCase(param.getOperateFlag()) ? "借阅失败" : "归还失败");
            }

        } else {
            if (byId.getState() != 1) {
                throw new BusinessException("该书籍不能重复进行归还");
            }
            if (byId.getLoanOutUserId() != userInfoVo.getId().longValue()) {
                throw new BusinessException("您不能进行此归还操作");
            }
            int i = this.baseMapper.addStock(byId.getId(), byId.getVersion());
            if (i != 1) {
                log.error("新增库存失败");
                throw new BusinessException(CommonConstants.BOOK_OPERATE_LOAN_OUT.equalsIgnoreCase(param.getOperateFlag()) ? "借阅失败" : "归还失败");
            }
        }

        // 新建书籍借阅信息
        LibraryBookBorrowLog libraryBookBorrowLog = new LibraryBookBorrowLog();
        libraryBookBorrowLog.setBookId(byId.getId());
        libraryBookBorrowLog.setBookInfo(JSON.toJSONString(byId));
        libraryBookBorrowLog.setTypeFlag(param.getOperateFlag());
        libraryBookBorrowLog.setUserId(userInfoVo.getId());
        int insert = libraryBookBorrowLogMapper.insert(libraryBookBorrowLog);
        if (insert != 1) {
            log.error("新增记录日志表失败");
            throw new BusinessException(CommonConstants.BOOK_OPERATE_LOAN_OUT.equalsIgnoreCase(param.getOperateFlag()) ? "借阅失败" : "归还失败");
        }



    }
}




