package com.caitb.library_manager.base.mapper;

import com.caitb.library_manager.base.domain.LibraryBook;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @Entity com.caitb.library_manager.base.domain.LibraryBook
 */
public interface LibraryBookMapper extends BaseMapper<LibraryBook> {

    /**
     * 增库存
     * @param id
     * @return
     */
    @Update("update library_book set stock = stock + 1,version = version + 1, state = 0, loan_out_user_id = null where id = #{id} and version = #{version}")
    int addStock(@Param("id")Long id, @Param("version")Integer version);

    @Update("update library_book set stock = stock - 1,version = version + 1,state = 1, loan_out_user_id = #{userId} where id = #{id} and version = #{version} and stock >= 1")
    int minusStock(@Param("id")Long id, @Param("userId")Long userId, @Param("version")Integer version);
}




