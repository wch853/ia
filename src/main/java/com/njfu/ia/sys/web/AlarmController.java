package com.njfu.ia.sys.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njfu.ia.sys.domain.AlarmRecord;
import com.njfu.ia.sys.service.AlarmRecordService;
import com.njfu.ia.sys.utils.PaginationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping(value = "/record/data")
    public @ResponseBody
    PaginationResult getAlarmRecordData(int limit, int offset, AlarmRecord alarmRecord, String start, String end) {
        PageHelper.offsetPage(offset, limit);
        List<AlarmRecord> alarmRecords = alarmRecordService.getAlarmRecords(alarmRecord, start, end);
        PageInfo<AlarmRecord> page = new PageInfo<>(alarmRecords);
        return new PaginationResult<>(page.getTotal(), alarmRecords);
    }
}
