package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.Memo;
import com.njfu.ia.sys.exception.BusinessException;

import java.util.List;

public interface MemoService {

    /**
     * 根据记录类型获取记录列表
     *
     * @param type
     * @return
     */
    List<Memo> queryMemoList(Integer type);

    /**
     * 根据记录编号查找记录
     *
     * @param id
     * @return
     */
    Memo queryMemo(int id);

    /**
     * 新增记录
     *
     * @param memo
     * @throws BusinessException
     */
    void addMemo(Memo memo) throws BusinessException;

    /**
     * 修改记录
     *
     * @param memo
     * @throws BusinessException
     */
    void modifyMemo(Memo memo) throws BusinessException;

    /**
     * 删除记录
     *
     * @param id
     * @throws BusinessException
     */
    void removeMemo(int id) throws BusinessException;
}
