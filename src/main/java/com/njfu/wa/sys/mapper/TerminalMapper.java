package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Terminal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TerminalMapper {

    /**
     * 获取终端列表
     *
     * @param terminal terminalId terminalType useStatus
     * @return List
     */
    List<Terminal> selectTerminals(Terminal terminal);

    /**
     * 新增终端信息
     *
     * @param terminal terminalId terminalType useStatus terminalPs
     * @return count
     */
    int insertTerminal(Terminal terminal);

    /**
     * 修改终端信息
     *
     * @param terminal terminalType useStatus terminalPs
     * @return count
     */
    int updateTerminal(Terminal terminal);

    /**
     * 删除终端信息
     *
     * @param terminalId terminalId
     * @return count
     */
    int deleteTerminal(@Param("terminalId") String terminalId);
}
