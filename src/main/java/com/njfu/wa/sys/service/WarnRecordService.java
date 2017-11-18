package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.WarnRecord;
import com.njfu.wa.sys.utils.Result;

import java.util.List;

public interface WarnRecordService {

    /**
     * 扫描大棚状态表，出现异常数据，插入报警记录
     */
    void scanFieldStatus();

    /**
     * 查询报警记录
     *
     * @param warnRecord fieldId warnType warnTime flag
     * @param start      start
     * @param end        end
     * @return data
     */
    List<WarnRecord> getWarnRecord(WarnRecord warnRecord, String start, String end);

    /**
     * 查询未处理报警记录
     *
     * @return data
     */
    List<WarnRecord> getUnHandleWarnRecord();

    /**
     * 修改报警记录处理标志
     *
     * @param ids  id
     * @param flag flag
     * @return message
     */
    Result modifyWarnRecord(Integer[] ids, String flag);

    /**
     * 获取未处理报警记录数量
     *
     * @return count
     */
    int getUnhandleRecordCount();
}
