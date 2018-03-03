package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.Block;
import com.njfu.ia.sys.domain.Vehicle;
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
public class VehicleServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleServiceTest.class);

    @Resource
    private VehicleService vehicleService;

    @Test
    public void getVehicles() throws Exception {
        List<Vehicle> vehicles = vehicleService.getVehicles(new Vehicle(), new Block());
        LOGGER.info("vehicles: {}", vehicles);
    }

    @Test
    public void addVehicle() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId("v000");
        vehicle.setVehicleType("xyz001");
        vehicle.setUseStatus("0");

        try {
            vehicleService.addVehicle(vehicle, new Block());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void modifyVehicle() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId("v001");
        vehicle.setVehicleType("xyz001");
        vehicle.setUseStatus("0");

        try {
            vehicleService.modifyVehicle(vehicle, new Block());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void removeVehicle() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId("v001");

        try {
            vehicleService.removeVehicle(vehicle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}