package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.Block;
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
public class BlockServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlockServiceTest.class);

    @Resource
    private BlockService blockService;

    @Test
    public void getBlocks() throws Exception {
        List<Block> blocks = blockService.getBlocks(new Block());
        LOGGER.info("blocks: {}", blocks);
    }

    @Test
    public void getBlocksAndFields() throws Exception {
        List<Block> blocksAndFields = blockService.getBlocksAndFields();
        LOGGER.info("blocksAndFields: {}", blocksAndFields);
    }

    @Test
    public void addBlock() throws Exception {
        Block block = new Block();
        block.setBlockName("test");

        try {
            blockService.addBlock(block);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void modifyBlock() throws Exception {
        Block block = new Block();
        block.setBlockName("test");

        try {
            blockService.modifyBlock(block);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void removeBlock() throws Exception {
        Block block = new Block();

        try {
            blockService.removeBlock(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}