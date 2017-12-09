package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.Terminal;
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
public class TerminalServiceTest {

    @Resource
    private TerminalService terminalService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TerminalServiceTest.class);

    @Test
    public void getTerminals() throws Exception {
        List<Terminal> terminals = terminalService.getTerminals(new Terminal());
        LOGGER.info("Terminals: {}", terminals);
    }

    @Test
    public void addTerminal() throws Exception {
        Terminal terminal = new Terminal();
        terminal.setTerminalId("t00");
        terminal.setTerminalType("test");
        terminal.setUseStatus("1");

        try {
            terminalService.addTerminal(terminal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void modifyTerminal() throws Exception {
        Terminal terminal = new Terminal();
        terminal.setTerminalId("t00");
        terminal.setTerminalType("test");
        terminal.setUseStatus("1");

        try {
            terminalService.modifyTerminal(terminal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void removeTerminal() throws Exception {
        String terminalId = "t01";

        try {
            terminalService.removeTerminal(terminalId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}