package com.njfu.ia.sys.mapper;

import com.njfu.ia.sys.domain.Terminal;
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
public class TerminalMapperTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TerminalMapperTest.class);
    @Resource
    private TerminalMapper terminalMapper;

    @Test
    public void selectTerminals() throws Exception {
        Terminal terminal = new Terminal();
        terminal.setTerminalId("t01");
        terminal.setUseStatus("1");
        List<Terminal> terminals = terminalMapper.selectTerminals(terminal);
        LOGGER.info("terminals: {}", terminals);
    }

    @Test
    public void insertTerminal() throws Exception {
        Terminal terminal = new Terminal();
        terminal.setTerminalId("t00");
        terminal.setTerminalType("test");
        terminal.setUseStatus("1");
        int res = terminalMapper.insertTerminal(terminal);
        Assert.assertEquals(1, res);
    }

    @Test
    public void updateTerminal() throws Exception {
        Terminal terminal = new Terminal();
        terminal.setTerminalId("t01");
        terminal.setTerminalType("test");
        terminal.setUseStatus("1");
        int res = terminalMapper.updateTerminal(terminal);
        Assert.assertEquals(1, res);
    }

    @Test
    public void deleteTerminal() throws Exception {
        String terminalId = "t01";
        int res = terminalMapper.deleteTerminal(terminalId);
        Assert.assertEquals(1, res);
    }

}