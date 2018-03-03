package com.njfu.ia.sys.mapper;

import com.njfu.ia.sys.domain.IrrigationPlan;
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
public class IrrigationPlanMapperTest {

    @Resource
    private IrrigationPlanMapper irrigationPlanMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(IrrigationPlanMapperTest.class);

    @Test
    public void selectIrrigationPlans() throws Exception {
        IrrigationPlan irrigationPlan = new IrrigationPlan();
        List<IrrigationPlan> irrigationPlans = irrigationPlanMapper.selectIrrigationPlans(irrigationPlan);
        LOGGER.info("irrigationPlans: {}", irrigationPlans);
    }

    @Test
    public void insertIrrigationPlan() throws Exception {
        IrrigationPlan irrigationPlan = new IrrigationPlan();
        irrigationPlan.setPlanVolume(500D);
        irrigationPlan.setDuration(100);
        int count = irrigationPlanMapper.insertIrrigationPlan(irrigationPlan);
        Assert.assertEquals(1, count);
    }

    @Test
    public void updateIrrigationPlan() throws Exception {
        IrrigationPlan irrigationPlan = new IrrigationPlan();
        irrigationPlan.setId(1);
        irrigationPlan.setPlanVolume(500D);
        int count = irrigationPlanMapper.updateIrrigationPlan(irrigationPlan);
        Assert.assertEquals(1, count);
    }

    @Test
    public void deleteIrrigationPlan() throws Exception {
        int count = irrigationPlanMapper.deleteIrrigationPlan(1);
        Assert.assertEquals(1, count);
    }

}