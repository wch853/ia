package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.Memo;
import com.njfu.wa.sys.exception.BusinessException;

import java.util.List;

public interface MemoService {

    /**
     * 根据记录类型获取记录列表
     *
     * @param type type
     * @return data
     */
    List<Memo> getMemoList(String type);

    /**
     * 根据记录编号查找记录
     *
     * @param id id
     * @return data
     */
    Memo getMemo(int id);

    /**
     * 新增记录
     *
     * @param memo memo
     * @throws BusinessException BusinessException
     */
    void addMemo(Memo memo) throws BusinessException;

    /**
     * 修改记录
     *
     * @param memo memo
     * @throws BusinessException BusinessException
     */
    void modifyMemo(Memo memo) throws BusinessException;

    /**
     * 删除记录
     *
     * @param id id
     * @throws BusinessException BusinessException
     */
    void removeMemo(int id) throws BusinessException;
}
