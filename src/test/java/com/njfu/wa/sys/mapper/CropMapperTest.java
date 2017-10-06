package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Crop;
import org.junit.Assert;
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
public class CropMapperTest {

    private static final Logger log = LoggerFactory.getLogger(CropMapperTest.class);

    @Autowired
    private CropMapper cropMapper;

    @Test
    public void selectCrops() throws Exception {
        List<Crop> crops = cropMapper.selectCrops(new Crop());
        log.info("crops: {}", crops);
    }

    @Test
    public void insertCrop() throws Exception {
        Crop crop = new Crop();
        crop.setCropId("c00");
        crop.setCropName("test");
        crop.setCropPs("test");

        int res = cropMapper.insertCrop(crop);
        Assert.assertEquals(1, res);
    }

    @Test
    public void updateCrop() throws Exception {
        Crop crop = new Crop();
        crop.setCropId("c001");
        crop.setCropName("test");
        crop.setCropPs("test");

        int res = cropMapper.updateCrop(crop);
        Assert.assertEquals(1, res);
    }

    @Test
    public void deleteCrop() throws Exception {
        Crop crop = new Crop();
        crop.setCropId("c001");

        int res = cropMapper.deleteCrop(crop);
        Assert.assertEquals(1, res);
    }

}