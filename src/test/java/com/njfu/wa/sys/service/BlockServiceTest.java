package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.Block;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlockServiceTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BlockService blockService;

    @Test
    public void getBlocks() throws Exception {
        List<Block> blocks = blockService.getBlocks(new Block());
        log.info("blocks: {}", blocks);
    }

}