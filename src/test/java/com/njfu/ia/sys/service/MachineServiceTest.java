package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.Block;
import com.njfu.ia.sys.domain.Machine;
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
public class MachineServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MachineServiceTest.class);

    @Resource
    private MachineService machineService;

    @Test
    public void getMachines() throws Exception {
        List<Machine> machines = machineService.getMachines(new Machine(), new Block());
        LOGGER.info("machines: {}", machines);
    }

    @Test
    public void addMachine() throws Exception {
        Machine machine = new Machine();
        machine.setMachineId("m000");
        machine.setMachineType("xyz");
        Block block = new Block();
        machine.setUseStatus("0");
        machine.setMachinePs("test");

        try {
            machineService.addMachine(machine, block);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void modifyMachine() throws Exception {
        Machine machine = new Machine();
        machine.setMachineId("m001");
        machine.setMachineType("xyz");

        Block block = new Block();

        machine.setUseStatus("0");
        machine.setMachinePs("test");

        try {
            machineService.modifyMachine(machine, block);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void removeMachine() throws Exception {
        Machine machine = new Machine();
        machine.setMachineId("m001");

        try {
            machineService.removeMachine(machine);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}