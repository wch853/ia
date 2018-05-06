package com.njfu.ia.sys.web;

import com.github.pagehelper.PageInfo;
import com.njfu.ia.sys.domain.ChartData;
import com.njfu.ia.sys.domain.DataType;
import com.njfu.ia.sys.domain.UpstreamDataRecord;
import com.njfu.ia.sys.enums.ResultEnum;
import com.njfu.ia.sys.exception.BusinessException;
import com.njfu.ia.sys.service.DataTypeService;
import com.njfu.ia.sys.service.FieldService;
import com.njfu.ia.sys.service.SensorService;
import com.njfu.ia.sys.service.UpstreamDataRecordService;
import com.njfu.ia.sys.utils.PaginationResult;
import com.njfu.ia.sys.utils.Result;
import com.njfu.ia.sys.utils.page.PageOffset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据管理控制器
 */
@Controller
@RequestMapping("/sys/data")
public class DataController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataController.class);

    @Resource
    private FieldService fieldService;

    @Resource
    private SensorService sensorService;

    @Resource
    private DataTypeService dataTypeService;

    @Resource
    private UpstreamDataRecordService upstreamDataRecordService;

    /**
     * 数据查询页面
     *
     * @return
     */
    @GetMapping("/query")
    public String dataQuery() {
        return "sys/data/query";
    }

    /**
     * 获取数据列表
     *
     * @param offset
     * @param limit
     * @param dataRecord
     * @param start
     * @param end
     * @return json data
     */
    @GetMapping("/record")
    @PageOffset
    public @ResponseBody
    PaginationResult getDataRecord(Integer offset, Integer limit, UpstreamDataRecord dataRecord, String start, String end) {
        List<UpstreamDataRecord> dataRecords = upstreamDataRecordService.queryDataRecords(dataRecord, start, end);
        PageInfo<UpstreamDataRecord> page = new PageInfo<>(dataRecords);
        return new PaginationResult<>(page.getTotal(), dataRecords);
    }

    /**
     * 数据分析页面
     *
     * @return Page
     */
    @GetMapping("/chart")
    public String dataChart() {
        return "sys/data/chart";
    }

    /**
     * 获取图表数据
     *
     * @param dataTypes dataTypes
     * @param fieldId   fieldId
     * @return json data
     */
    @PostMapping("/chart")
    public @ResponseBody
    Result<ChartData> getChartData(@RequestParam("dataTypes[]") Integer[] dataTypes, Integer sectionId, String start, String end) {
        try {
            ChartData chartData = upstreamDataRecordService.constructChartData(dataTypes, sectionId, start, end);
            return Result.response(ResultEnum.SUCCESS, null, chartData);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error("consutruct chart data Exception", e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 数据类型页面
     *
     * @return Page
     */
    @GetMapping("/type")
    public String dataTypes() {
        return "sys/data/type";
    }

    /**
     * 获取数据类型
     *
     * @param offset   offset
     * @param limit    limit
     * @param dataType dataType
     * @return json data
     */
    @GetMapping("/type/data")
    @PageOffset
    public @ResponseBody
    PaginationResult getDataTypes(Integer offset, Integer limit, DataType dataType) {
        List<DataType> dataTypes = dataTypeService.queryDataTypes(dataType);
        PageInfo<DataType> page = new PageInfo<>(dataTypes);
        return new PaginationResult<>(page.getTotal(), dataTypes);
    }

    /**
     * 新增数据类型
     *
     * @param dataType
     * @return
     */
    @PostMapping(value = "/type/add")
    public @ResponseBody
    Result addDataType(DataType dataType) {
        try {
            dataTypeService.addDataType(dataType);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error("add data type Exception", e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 修改数据类型
     *
     * @param dataType
     * @return
     */
    @PostMapping("/type/modify")
    public @ResponseBody
    Result modifyDataType(DataType dataType) {
        try {
            dataTypeService.updateDataType(dataType);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error("modify data type Exception", e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 删除数据类型
     *
     * @param id
     * @return
     */
    @PostMapping("/type/remove")
    public @ResponseBody
    Result removeDataType(Integer id) {
        try {
            dataTypeService.reomveDataType(id);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error("remove data type Exception", e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 查询各区块当前各监控数据状态
     *
     * @param sectionId
     * @return
     */
    @GetMapping("/status")
    public @ResponseBody
    Result querySectionStatus(Integer sectionId) {
        try {
            List<UpstreamDataRecord> lastDataRecords = upstreamDataRecordService.queryLastDataRecords(sectionId);
            return Result.response(ResultEnum.SUCCESS, lastDataRecords);
        } catch (Exception e) {
            LOGGER.error("query section status Exception", e);
            return Result.response(ResultEnum.FAIL);
        }
    }
}
