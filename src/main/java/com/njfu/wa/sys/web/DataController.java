package com.njfu.wa.sys.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njfu.wa.sys.domain.*;
import com.njfu.wa.sys.enums.ResultEnum;
import com.njfu.wa.sys.exception.BusinessException;
import com.njfu.wa.sys.service.DataRecordService;
import com.njfu.wa.sys.service.FieldService;
import com.njfu.wa.sys.service.SensorService;
import com.njfu.wa.sys.utils.PaginationResult;
import com.njfu.wa.sys.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据管理控制器
 */
@Controller
@RequestMapping("/sys/data")
public class DataController {

    @Resource
    private DataRecordService dataRecordService;

    @Resource
    private FieldService fieldService;

    @Resource
    private SensorService sensorService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DataController.class);

    /**
     * 数据查询页面
     *
     * @return Page
     */
    @GetMapping("/query")
    public String dataQuery(Model model) {
        List<Field> fields = fieldService.getFields(new Field(), new Block(), new Crop());
        List<Sensor> sensors = sensorService.getSensors(new Sensor(), new Field());
        model.addAttribute("fields", fields);
        model.addAttribute("sensors", sensors);
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
        List<Field> fields = fieldService.getFields(new Field(), new Block(), new Crop());
        model.addAttribute("fields", fields);
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
            LOGGER.error(e.getMessage());
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }
}
