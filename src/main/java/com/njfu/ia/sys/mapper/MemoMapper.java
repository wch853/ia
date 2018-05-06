package com.njfu.ia.sys.mapper;

import com.njfu.ia.sys.domain.Memo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemoMapper {

    /**
     * 根据类型获取记录标题列表
     *
     * @param type
     * @return
     */
    List<Memo> selectMemoList(@Param("type") Integer type);

    /**
     * 根据id获取记录
     *
     * @param id
     * @return
     */
    Memo selectMemo(@Param("id") int id);

    /**
     * 新增记录
     *
     * @param memo
     * @return
     */
    int insertMemo(Memo memo);

    /**
     * 修改记录
     *
     * @param memo
     * @return
     */
    int updateMemo(Memo memo);

    /**
     * 删除记录
     *
     * @param id
     * @return
     */
    int deleteMemo(@Param("id") Integer id);
}
