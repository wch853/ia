package com.njfu.wa.sys.web;

import com.njfu.wa.sys.enums.ResultEnum;
import com.njfu.wa.sys.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常控制器
 */
@ControllerAdvice
public class ExceptionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    /**
     * 处理非法数据输入异常
     *
     * @return json message
     */
    @ExceptionHandler({DataIntegrityViolationException.class, BindException.class})
    public @ResponseBody
    Result dataIntegrityViolationException() {
        return Result.response(ResultEnum.FAIL);
    }

    /**
     * 统一异常处理
     *
     * @param e e
     * @return page
     */
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        LOGGER.error(e.getMessage(), e);
        return "error";
    }
}
