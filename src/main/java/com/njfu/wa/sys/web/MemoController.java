package com.njfu.wa.sys.web;

import com.njfu.wa.sys.domain.Memo;
import com.njfu.wa.sys.enums.ResultEnum;
import com.njfu.wa.sys.service.MemoService;
import com.njfu.wa.sys.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 记录管理控制器
 */
@Controller
@RequestMapping("sys/memo")
public class MemoController {

    @Resource
    private MemoService memoService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MemoController.class);

    @RequestMapping
    public String memo() {
        return "sys/memo/log";
    }

    /**
     * 获取指定类型下的记录列表
     *
     * @param type type
     * @return data
     */
    @GetMapping("/getMemoList")
    public @ResponseBody
    Result getMemoList(String type) {
        List<Memo> memoList = memoService.getMemoList(type);
        return Result.response(ResultEnum.DATA, memoList);
    }

    /**
     * 获取指定id对应的记录
     *
     * @param id id
     * @return data
     */
    @GetMapping("/getMemo")
    public @ResponseBody
    Result getMemo(Integer id) {
        Memo memo = memoService.getMemo(id);
        return Result.response(ResultEnum.DATA, memo);
    }

    /**
     * 新增记录
     *
     * @param memo memo
     * @return message
     */
    @PostMapping("/addMemo")
    public @ResponseBody
    Result addMemo(Memo memo) {
        try {
            memoService.addMemo(memo);
            return Result.response(ResultEnum.SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 修改记录
     *
     * @param memo memo
     * @return message
     */
    @PostMapping("/modifyMemo")
    public @ResponseBody
    Result modifyMemo(Memo memo) {
        try {
            memoService.modifyMemo(memo);
            return Result.response(ResultEnum.SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }

    /**
     * 删除记录
     *
     * @param id id
     * @return message
     */
    @PostMapping("/removeMemo")
    public @ResponseBody
    Result removeMemo(Integer id) {
        try {
            memoService.removeMemo(id);
            return Result.response(ResultEnum.SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.response(ResultEnum.FAIL);
        }
    }
}
