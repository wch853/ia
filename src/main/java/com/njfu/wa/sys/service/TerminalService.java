package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.Terminal;

import java.util.List;

public interface TerminalService {

    /**
     * 获取终端列表
     *
     * @param terminal terminalId terminalType useStatus
     * @return List
     */
    List<Terminal> getTerminals(Terminal terminal);

    /**
     * 新增终端信息
     *
     * @param terminal terminalId terminalType useStatus terminalPs
     */
    void addTerminal(Terminal terminal);

    /**
     * 修改终端信息
     *
     * @param terminal terminalType useStatus terminalPs
     */
    void modifyTerminal(Terminal terminal);

    /**
     * 删除终端信息
     *
     * @param terminalId terminalId
     */
    void removeTerminal(String terminalId);
}
