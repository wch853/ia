package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.Memo;
import com.njfu.wa.sys.exception.BusinessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemoServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemoServiceTest.class);
    @Resource
    private MemoService memoService;

    @Test
    public void getMemoList() throws Exception {
        List<Memo> memoList = memoService.getMemoList("1");
        LOGGER.info("memoList: {}", memoList);
    }

    @Test
    public void getMemo() throws Exception {
        Memo memo = memoService.getMemo(1);
        LOGGER.info("memo: {}", memo);
    }

    @Test
    public void addMemo() throws Exception {
        Memo memo = new Memo();
        memo.setTitle("test");
        memo.setType("1");
        memo.setContent("test");
        memo.setUpdateUser("root");

        try {
            memoService.addMemo(memo);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void modifyMemo() throws Exception {
        Memo memo = new Memo();
        memo.setId(1);
        memo.setTitle("test");
        memo.setUpdateUser("root");

        try {
            memoService.modifyMemo(memo);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void removeMemo() throws Exception {
        try {
            memoService.removeMemo(1);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }

}