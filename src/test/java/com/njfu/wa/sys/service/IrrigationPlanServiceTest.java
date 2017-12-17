package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.IrrigationPlan;
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
public class IrrigationPlanServiceTest {

    @Resource
    private IrrigationPlanService irrigationPlanService;

    private static final Logger LOGGER = LoggerFactory.getLogger(IrrigationPlanServiceTest.class);

    @Test
    public void getIrrigationPlans() throws Exception {
        IrrigationPlan irrigationPlan = new IrrigationPlan();
        List<IrrigationPlan> irrigationPlans = irrigationPlanService.getIrrigationPlans(irrigationPlan);
        LOGGER.info("irrigationPlans: {}", irrigationPlans);
    }

    @Test
    public void addIrrigationPlan() throws Exception {
        IrrigationPlan irrigationPlan = new IrrigationPlan();
        irrigationPlan.setPlanVolume(500D);
        irrigationPlan.setDuration(100);

        try {
            irrigationPlanService.addIrrigationPlan(irrigationPlan);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void modifyIrrigationPlan() throws Exception {
        IrrigationPlan irrigationPlan = new IrrigationPlan();
        irrigationPlan.setId(1);
        irrigationPlan.setPlanVolume(500D);

        try {
            irrigationPlanService.modifyIrrigationPlan(irrigationPlan);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void removeIrrigationPlan() throws Exception {
        try {
            irrigationPlanService.removeIrrigationPlan(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}