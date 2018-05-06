package com.njfu.ia.sys.service.impl;

import com.njfu.ia.sys.domain.Memo;
import com.njfu.ia.sys.domain.User;
import com.njfu.ia.sys.exception.BusinessException;
import com.njfu.ia.sys.mapper.MemoMapper;
import com.njfu.ia.sys.service.MemoService;
import com.njfu.ia.sys.utils.Constants;
import org.apache.shiro.SecurityUtils;
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
     * @param type
     * @return
     */
    @Override
    public List<Memo> queryMemoList(Integer type) {
        return memoMapper.selectMemoList(type);
    }

    /**
     * 根据记录编号查找记录
     *
     * @param id
     * @return
     */
    @Override
    public Memo queryMemo(int id) {
        return memoMapper.selectMemo(id);
    }

    /**
     * 新增记录
     *
     * @param memo
     * @throws BusinessException
     */
    @Override
    public void addMemo(Memo memo) throws BusinessException {
        memo.setUpdateUser(this.getUpdateUser());
        int res = memoMapper.insertMemo(memo);
        if (res <= 0) {
            throw new BusinessException("新增记录失败！");
        }
    }

    /**
     * 修改记录
     *
     * @param memo
     * @throws BusinessException
     */
    @Override
    public void modifyMemo(Memo memo) throws BusinessException {
        memo.setUpdateUser(this.getUpdateUser());
        int res = memoMapper.updateMemo(memo);
        if (res <= 0) {
            throw new BusinessException("新增记录失败！");
        }
    }

    /**
     * 删除记录
     *
     * @param id
     * @throws BusinessException
     */
    @Override
    public void removeMemo(int id) throws BusinessException {
        int res = memoMapper.deleteMemo(id);
        if (res <= 0) {
            throw new BusinessException("删除记录失败！");
        }
    }

    /**
     * 获取编辑用户信息
     *
     * @return
     */
    private String getUpdateUser() {
        String name = Constants.DEFAULT_USER_NAME;
        if (Constants.USE_SHIRO) {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            name = user.getName();
        }
        return name;
    }
}
