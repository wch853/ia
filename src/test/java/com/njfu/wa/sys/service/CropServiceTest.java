package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.Crop;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CropServiceTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CropService cropService;

    @Test
    public void getCrops() throws Exception {
        List<Crop> crops = cropService.getCrops();
        log.info("crops : {}", crops);
    }

}