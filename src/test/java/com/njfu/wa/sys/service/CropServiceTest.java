package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.Crop;
import com.njfu.wa.sys.util.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CropServiceTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CropService cropService;

    @Test
    public void getCrops() throws Exception {
        List<Crop> crops = cropService.getCrops(new Crop());
        log.info("crops: {}", crops);
    }

    @Test
    public void addCrop() throws Exception {
        Crop crop = new Crop();
        crop.setCropId("c000");
        crop.setCropName("test");
        crop.setCropPs("test");

        Message message = cropService.addCrop(crop);
        log.info("message: {}", message);
    }

    @Test
    public void modifyCrop() throws Exception {
        Crop crop = new Crop();
        crop.setCropId("c001");
        crop.setCropName("test");
        crop.setCropPs("test");

        Message message = cropService.modifyCrop(crop);
        log.info("message: {}", message);
    }

    @Test
    public void removeCrop() throws Exception {
        Crop crop = new Crop();
        crop.setCropId("c001");

        Message message = cropService.removeCrop(crop);
        log.info("message: {}", message);
    }

}