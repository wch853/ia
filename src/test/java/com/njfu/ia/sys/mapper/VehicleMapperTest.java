package com.njfu.ia.sys.mapper;

import com.njfu.ia.sys.domain.Block;
import com.njfu.ia.sys.domain.Vehicle;
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
public class VehicleMapperTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleMapperTest.class);

    @Resource
    private VehicleMapper vehicleMapper;

    @Test
    public void selectVehicles() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.setBlock(new Block());

        List<Vehicle> vehicles = vehicleMapper.selectVehicles(vehicle);
        LOGGER.info("vehicles: {}", vehicles);
    }

    @Test
    public void insertVehicle() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId("v000");
        vehicle.setVehicleType("xyz001");
        vehicle.setUseStatus("0");
        vehicle.setBlock(new Block());

        int res = vehicleMapper.insertVehicle(vehicle);

        Assert.assertEquals(1, res);
    }

    @Test
    public void updateVehicle() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId("v001");
        vehicle.setVehicleType("xyz001");
        vehicle.setUseStatus("0");
        vehicle.setBlock(new Block());

        int res = vehicleMapper.updateVehicle(vehicle);

        Assert.assertEquals(1, res);
    }

    @Test
    public void deleteVehicle() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId("v001");

        int res = vehicleMapper.deleteVehicle(vehicle);

        Assert.assertEquals(1, res);
    }

    @Test
    public void updateVehicleByBlock() throws Exception {
        int res = vehicleMapper.updateVehicleByBlock("b01");
        Assert.assertNotEquals(0, res);
    }

}