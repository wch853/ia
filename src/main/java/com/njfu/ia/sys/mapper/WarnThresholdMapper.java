package com.njfu.ia.sys.mapper;

import com.njfu.ia.sys.domain.WarnThreshold;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WarnThresholdMapper {

    /**
     * 查询阈值信息
     *
     * @param warnThreshold warnType
     * @return data
     */
    List<WarnThreshold> selectWarnThreshold(WarnThreshold warnThreshold);

    /**
     * 修改阈值信息
     *
     * @param warnThreshold ceil floor useStatus
     * @return row count
     */
    int updateWarnThreshold(WarnThreshold warnThreshold);
}
