package com.njfu.ia.sys.mapper;

import com.njfu.ia.sys.domain.DataRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DataRecordMapper {

    /**
     * 查询数据记录
     *
     * @param map map
     * @return data
     */
    List<DataRecord> selectDataRecords(Map<String, Object> map);

    /**
     * 查询生成图表所需的数据
     *
     * @param map map
     * @return data
     */
    List<DataRecord> selectChartData(Map<String, Object> map);
}
