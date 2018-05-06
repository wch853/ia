package com.njfu.ia.sys.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njfu.ia.sys.domain.AlarmRecord;
import com.njfu.ia.sys.enums.ResultEnum;
import com.njfu.ia.sys.exception.BusinessException;
import com.njfu.ia.sys.service.AlarmRecordService;
import com.njfu.ia.sys.utils.PaginationResult;
import com.njfu.ia.sys.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/sys/alarm")
public class AlarmController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmController.class);

    @Resource
    private AlarmRecordService alarmRecordService;

    /**
     * 报警记录页面
     *
     * @return Page
     */
    @GetMapping("/record")
    public String alarmRecord() {
        return "sys/alarm/record";
    }

    /**
     * 查询报警记录
     *
     * @param limit
     * @param offset
     * @param alarmRecord
     * @param start
     * @param end
     * @return
     */
    @GetMapping(value = "/record/data")
    public @ResponseBody
    PaginationResult getAlarmRecordData(Integer limit, Integer offset, AlarmRecord alarmRecord, String start, String end) {
        PageHelper.offsetPage(offset, limit);
        List<AlarmRecord> alarmRecords = alarmRecordService.queryAlarmRecords(alarmRecord, start, end);
        PageInfo<AlarmRecord> page = new PageInfo<>(alarmRecords);
        return new PaginationResult<>(page.getTotal(), alarmRecords);
    }

    /**
     * 报警详情页面
     *
     * @return
     */
    @GetMapping("/detail")
    public String detail() {
        return "sys/alarm/detail";
    }

    /**
     * 获取未处理报警数据
     *
     * @return
     */
    @GetMapping("/record/unhandle")
    public @ResponseBody
    Result getUnHandleRecord() {
        List<AlarmRecord> unhandleRecords = alarmRecordService.queryUnhandleRecords();
        return Result.response(ResultEnum.DATA, unhandleRecords);
    }

    /**
     * 修改报警记录处理状态
     *
     * @param ids
     * @param flag
     * @return
     */
    @PostMapping("/record/modify")
    public @ResponseBody
    Result modifyWarnRecord(@RequestParam("ids[]") Integer[] ids, Integer flag) {
        try {
            alarmRecordService.modifyAlarmRecordFlag(ids, flag);
            return Result.response(ResultEnum.SUCCESS);
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL, e.getMessage(), null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 获取未处理报警记录数量
     *
     * @return json data
     */
    @GetMapping("/record/unhandle/count")
    public @ResponseBody
    Result getUnhandleRecordCount() {
        return Result.response(ResultEnum.WARN, alarmRecordService.queryUnhandleRecordsCount());
    }
}
