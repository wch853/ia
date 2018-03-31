package com.njfu.ia.sys.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njfu.ia.sys.domain.*;
import com.njfu.ia.sys.enums.ResultEnum;
import com.njfu.ia.sys.exception.BusinessException;
import com.njfu.ia.sys.service.DataRecordService;
import com.njfu.ia.sys.service.DataTypeService;
import com.njfu.ia.sys.service.FieldService;
import com.njfu.ia.sys.service.SensorService;
import com.njfu.ia.sys.utils.PaginationResult;
import com.njfu.ia.sys.utils.Result;
import com.njfu.ia.sys.utils.page.PageOffset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private DataRecordService dataRecordService;

    @Resource
    private FieldService fieldService;

    @Resource
    private SensorService sensorService;

    @Resource
    private DataTypeService dataTypeService;

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
     * @param offset     offset
     * @param limit      limit
     * @param dataRecord dataRecord
     * @param start      start
     * @param end        end
     * @return json data
     */
    @GetMapping("/record")
    public @ResponseBody
    PaginationResult getDataRecord(int offset, int limit, DataRecord dataRecord, String start, String end) {
        PageHelper.offsetPage(offset, limit);
        List<DataRecord> dataRecords = dataRecordService.getDataRecords(dataRecord, start, end);
        PageInfo<DataRecord> page = new PageInfo<>(dataRecords);
        return new PaginationResult<>(page.getTotal(), dataRecords);
    }

    /**
     * 数据分析页面
     *
     * @return Page
     */
    @GetMapping("/analysis")
    public String dataAnalysis(Model model) {
        return "sys/data/analysis";
    }

    /**
     * 获取图表数据
     *
     * @param dataTypes dataTypes
     * @param fieldId   fieldId
     * @return json data
     */
    @GetMapping("/chart")
    public @ResponseBody
    Result<ChartData> getChartData(@RequestParam("dataTypes[]") String[] dataTypes, String fieldId) {
        try {
            ChartData chartData = dataRecordService.getChartData(dataTypes, fieldId);
            return Result.response(ResultEnum.SUCCESS, null, chartData);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error("get chart data Exception", e);
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
    PaginationResult getDataTypes(int offset, int limit, DataType dataType) {
        List<DataType> dataTypes = dataTypeService.getDataTypes(dataType);
        PageInfo<DataType> page = new PageInfo<>(dataTypes);
        return new PaginationResult<>(page.getTotal(), dataTypes);
    }

    /**
     * 获取数据类型
     *
     * @param offset   offset
     * @param limit    limit
     * @param dataType dataType
     * @return json data
     */
    @GetMapping("/type/list")
    public @ResponseBody
    Result listDataTypes(DataType dataType) {
        return Result.response(ResultEnum.SUCCESS, null, dataTypeService.getDataTypes(dataType));
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
}
