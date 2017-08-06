package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Machine;
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
public class MachineMapperTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MachineMapper machineMapper;

    @Test
    public void selectMachines() throws Exception {
        List<Machine> machines = machineMapper.selectMachines();
        log.info("machines : {}", machines);
    }
}