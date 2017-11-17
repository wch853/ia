package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Block;
import com.njfu.wa.sys.domain.Machine;
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
public class MachineMapperTest {

    @Test
    public void updateVehicleByBlock() throws Exception {
    }

    private static final Logger log = LoggerFactory.getLogger(MachineMapperTest.class);

    @Resource
    private MachineMapper machineMapper;

    @Test
    public void selectMachines() throws Exception {
        Block block = new Block();
        Machine machine = new Machine();
        machine.setBlock(block);

        List<Machine> machines = machineMapper.selectMachines(machine);
        log.info("machines: {}", machines);
    }

    @Test
    public void insertMachine() throws Exception {
        Machine machine = new Machine();
        machine.setMachineId("m000");
        machine.setMachineType("xyz");

        Block block = new Block();
        block.setBlockId("b01");
        machine.setBlock(block);

        machine.setUseStatus("0");
        machine.setMachinePs("test");

        int res = machineMapper.insertMachine(machine);

        Assert.assertEquals(1, res);
    }

    @Test
    public void updateMachine() throws Exception {
        Machine machine = new Machine();
        machine.setMachineId("m001");
        machine.setMachineType("xyz");

        Block block = new Block();
        block.setBlockId("b01");
        machine.setBlock(block);

        machine.setUseStatus("0");
        machine.setMachinePs("test");

        int res = machineMapper.updateMachine(machine);

        Assert.assertEquals(1, res);
    }

    @Test
    public void deleteMachine() throws Exception {
        Machine machine = new Machine();
        machine.setMachineId("m001");

        int res = machineMapper.deleteMachine(machine);

        Assert.assertEquals(1, res);
    }

    @Test
    public void updateMachineByBlock() throws Exception {
        int res = machineMapper.updateMachineByBlock("b01");

        Assert.assertEquals(1, res);
    }

}