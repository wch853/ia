package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.Block;
import com.njfu.wa.sys.domain.Machine;
import com.njfu.wa.sys.domain.util.Message;
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
public class MachineServiceTest {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MachineService machineService;

    @Test
    public void getMachines() throws Exception {
        List<Machine> machines = machineService.getMachines(new Machine(), new Block());
        log.info("machines: {}", machines);
    }

    @Test
    public void addMachine() throws Exception {
        Machine machine = new Machine();
        machine.setMachineId("m000");
        machine.setMachineType("xyz");

        Block block = new Block();
        block.setBlockId("b01");

        machine.setUseStatus("0");
        machine.setMachinePs("test");

        Message message = machineService.addMachine(machine, block);
        log.info("message: {}", machine);
    }

    @Test
    public void modifyMachine() throws Exception {
        Machine machine = new Machine();
        machine.setMachineId("m001");
        machine.setMachineType("xyz");

        Block block = new Block();
        block.setBlockId("b01");

        machine.setUseStatus("0");
        machine.setMachinePs("test");

        Message message = machineService.modifyMachine(machine, block);
        log.info("message: {}", machine);
    }

    @Test
    public void removeMachine() throws Exception {
        Machine machine = new Machine();
        machine.setMachineId("m001");

        Message message = machineService.removeMachine(machine);
        log.info("message: {}", machine);
    }

}