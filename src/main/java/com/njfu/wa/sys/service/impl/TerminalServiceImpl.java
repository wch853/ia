package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.domain.Terminal;
import com.njfu.wa.sys.exception.BusinessException;
import com.njfu.wa.sys.mapper.TerminalMapper;
import com.njfu.wa.sys.service.TerminalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TerminalServiceImpl implements TerminalService {

    @Resource
    private TerminalMapper terminalMapper;

    /**
     * 获取终端列表
     *
     * @param terminal terminalId terminalType useStatus
     * @return List
     */
    @Override
    public List<Terminal> getTerminals(Terminal terminal) {
        return terminalMapper.selectTerminals(terminal);
    }

    /**
     * 新增终端信息
     *
     * @param terminal terminalId terminalType useStatus terminalPs
     */
    @Override
    public void addTerminal(Terminal terminal) {
        this.convertNull(terminal);
        int count = terminalMapper.insertTerminal(terminal);
        if (count <= 0) {
            throw new BusinessException("新增终端信息失败，请检查新增编号是否存在！");
        }
    }

    /**
     * 修改终端信息
     *
     * @param terminal terminalType useStatus terminalPs
     */
    @Override
    public void modifyTerminal(Terminal terminal) {
        this.convertNull(terminal);
        int count = terminalMapper.updateTerminal(terminal);
        if (count <= 0) {
            throw new BusinessException("修改终端信息失败！");
        }
    }

    /**
     * 删除终端信息
     *
     * @param terminalId terminalId
     */
    @Override
    public void removeTerminal(String terminalId) {
        int count = terminalMapper.deleteTerminal(terminalId);
        if (count <= 0) {
            throw new BusinessException("删除终端信息失败！");
        }
    }

    /**
     * 使得terminalPs不为空字符串
     *
     * @param terminal terminal
     */
    private void convertNull(Terminal terminal) {
        if ("".equals(terminal.getTerminalPs())) {
            terminal.setTerminalPs(null);
        }
    }
}
