package com.njfu.wa.sys.web;

import com.njfu.wa.sys.enums.ResultEnum;
import com.njfu.wa.sys.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
     * 统一异常处理
     *
     * @return json message
     */
    @ExceptionHandler(Exception.class)
    public @ResponseBody
    Result exceptionHandler(Exception e) {
        LOGGER.error(e.getMessage());
        return Result.response(ResultEnum.FAIL, e.getMessage(), null);
    }

}
