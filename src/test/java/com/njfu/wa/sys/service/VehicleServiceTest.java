package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.Block;
import com.njfu.wa.sys.domain.Vehicle;
import com.njfu.wa.sys.domain.util.Result;
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
public class VehicleServiceTest {

    private static final Logger log = LoggerFactory.getLogger(VehicleServiceTest.class);

    @Autowired
    private VehicleService vehicleService;

    @Test
    public void getVehicles() throws Exception {
        List<Vehicle> vehicles = vehicleService.getVehicles(new Vehicle(), new Block());
        log.info("vehicles: {}", vehicles);
    }

    @Test
    public void addVehicle() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId("v000");
        vehicle.setVehicleType("xyz001");
        vehicle.setUseStatus("0");

        Result result = vehicleService.addVehicle(vehicle, new Block());

        log.info("result: {}", result);
    }

    @Test
    public void modifyVehicle() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId("v001");
        vehicle.setVehicleType("xyz001");
        vehicle.setUseStatus("0");

        Result result = vehicleService.modifyVehicle(vehicle, new Block());
        log.info("result: {}", result);
    }

    @Test
    public void removeVehicle() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId("v001");

        Result result = vehicleService.removeVehicle(vehicle);
        log.info("result: {}", result);
    }

}