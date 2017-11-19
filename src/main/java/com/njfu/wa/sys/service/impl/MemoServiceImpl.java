package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.domain.Memo;
import com.njfu.wa.sys.exception.BusinessException;
import com.njfu.wa.sys.mapper.MemoMapper;
import com.njfu.wa.sys.service.MemoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MemoServiceImpl implements MemoService {

    @Resource
    private MemoMapper memoMapper;

    /**
     * 根据记录类型获取记录列表
     *
     * @param type type
     * @return data
     */
    @Override
    public List<Memo> getMemoList(String type) {
        return memoMapper.selectMemoList(type);
    }

    /**
     * 根据记录编号查找记录
     *
     * @param id id
     * @return data
     */
    @Override
    public Memo getMemo(int id) {
        return memoMapper.selectMemo(id);
    }

    /**
     * 新增记录
     *
     * @param memo memo
     * @throws BusinessException BusinessException
     */
    @Override
    public void addMemo(Memo memo) throws BusinessException {
        int res = memoMapper.insertMemo(memo);
        if (res <= 0) {
            throw new BusinessException("新增记录失败！");
        }
    }

    /**
     * 修改记录
     *
     * @param memo memo
     * @throws BusinessException BusinessException
     */
    @Override
    public void modifyMemo(Memo memo) throws BusinessException {
        int res = memoMapper.updateMemo(memo);
        if (res <= 0) {
            throw new BusinessException("新增记录失败！");
        }
    }

    /**
     * 删除记录
     *
     * @param id id
     * @throws BusinessException BusinessException
     */
    @Override
    public void removeMemo(int id) throws BusinessException {
        int res = memoMapper.deleteMemo(id);
        if (res <= 0) {
            throw new BusinessException("删除记录失败！");
        }
    }
}
