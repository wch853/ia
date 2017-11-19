package com.njfu.wa.sys.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njfu.wa.sys.domain.*;
import com.njfu.wa.sys.service.DataRecordService;
import com.njfu.wa.sys.service.FieldService;
import com.njfu.wa.sys.service.SensorService;
import com.njfu.wa.sys.utils.PaginationResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    /**
     * 数据查询页面
     *
     * @return page
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
    @GetMapping("/getDataRecord")
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
     * @return page
     */
    @GetMapping("/analysis")
    public String dataAnalysis() {
        return "sys/data/analysis";
    }
}
