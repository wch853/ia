package com.njfu.wa.sys.mapper;


import com.njfu.wa.sys.domain.Block;
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
public class BlockMapperTest {

    private static final Logger log = LoggerFactory.getLogger(BlockMapperTest.class);

    @Resource
    private BlockMapper blockMapper;

    @Test
    public void selectBlocks() throws Exception {
        List<Block> blocks = blockMapper.selectBlocks(new Block());
        log.info("blocks: {}", blocks);
    }

    @Test
    public void selectBlocksAndFields() throws Exception {
        List<Block> blocks = blockMapper.selectBlocksAndFields();
        for (Block block : blocks) {
            log.info("block: {}", block.getFields());
        }
    }

    @Test
    public void insertBlock() throws Exception {
        Block block = new Block();
//        block.setBlockId("test");
//        block.setBlockName("test");
//        block.setBlockLoc("test");
//        block.setBlockPs("test");

        int res = blockMapper.insertBlock(block);
        Assert.assertEquals(1, res);
    }

    @Test
    public void updateBlock() throws Exception {
        Block block = new Block();
        block.setBlockId("b01");
        block.setBlockName("test");
        block.setBlockLoc("test");
        block.setBlockPs("test");

        int res = blockMapper.updateBlock(block);
        Assert.assertEquals(1, res);
    }

    @Test
    public void deleteBlock() throws Exception {
        Block block = new Block();
        block.setBlockId("b01");

        int res = blockMapper.deleteBlock(block);
        Assert.assertEquals(1, res);
    }

}