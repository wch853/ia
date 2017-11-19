package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Memo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemoMapper {

    /**
     * 根据类型获取记录标题列表
     *
     * @param type type
     * @return data
     */
    List<Memo> selectMemoList(@Param("type") String type);

    /**
     * 根据id获取记录
     *
     * @param id id
     * @return Memo
     */
    Memo selectMemo(@Param("id") int id);

    /**
     * 新增记录
     *
     * @param memo memo
     * @return count
     */
    int insertMemo(Memo memo);

    /**
     * 修改记录
     *
     * @param memo memo
     * @return count
     */
    int updateMemo(Memo memo);

    /**
     * 删除记录
     *
     * @param id id
     * @return count
     */
    int deleteMemo(@Param("id") int id);
}
