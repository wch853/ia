package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Memo;
import org.junit.Assert;
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
public class MemoMapperTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemoMapperTest.class);
    @Resource
    private MemoMapper memoMapper;

    @Test
    public void selectMemoList() throws Exception {
        List<Memo> memoList = memoMapper.selectMemoList("1");
        LOGGER.info("memoList: {}", memoList);
    }

    @Test
    public void selectMemo() throws Exception {
        Memo memo = memoMapper.selectMemo(1);
        LOGGER.info("memo: {}", memo);
    }

    @Test
    public void insertMemo() throws Exception {
        Memo memo = new Memo();
        memo.setTitle("test");
        memo.setType("1");
        memo.setContent("test");
        memo.setUpdateUser("root");
        int res = memoMapper.insertMemo(memo);
        Assert.assertEquals(1, res);
    }

    @Test
    public void updateMemo() throws Exception {
        Memo memo = new Memo();
        memo.setId(1);
        memo.setTitle("test");
        memo.setContent("test");
        memo.setUpdateUser("root");
        int res = memoMapper.updateMemo(memo);
        Assert.assertEquals(1, res);
    }

    @Test
    public void deleteMemo() throws Exception {
        int res = memoMapper.deleteMemo(1);
        Assert.assertEquals(1, res);
    }

}