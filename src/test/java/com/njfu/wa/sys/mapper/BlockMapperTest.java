package com.njfu.wa.sys.mapper;


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
public class BlockMapperTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BlockMapper blockMapper;

    @Test
    public void selectBlocks() throws Exception {
        List<Block> blocks = blockMapper.selectBlocks();
        log.info("blocks : {}", blocks);
    }

}