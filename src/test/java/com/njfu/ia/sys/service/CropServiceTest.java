package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.Crop;
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
public class CropServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CropServiceTest.class);

    @Resource
    private CropService cropService;

    @Test
    public void getCrops() throws Exception {
        List<Crop> crops = cropService.getCrops(new Crop());
        LOGGER.info("crops: {}", crops);
    }

    @Test
    public void addCrop() throws Exception {
        Crop crop = new Crop();
        crop.setCropId("c000");
        crop.setCropName("test");
        crop.setCropPs("test");

        try {
            cropService.addCrop(crop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void modifyCrop() throws Exception {
        Crop crop = new Crop();
        crop.setCropId("c001");
        crop.setCropName("test");
        crop.setCropPs("test");

        try {
            cropService.modifyCrop(crop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void removeCrop() throws Exception {
        Crop crop = new Crop();
        crop.setCropId("c001");

        try {
            cropService.removeCrop(crop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}