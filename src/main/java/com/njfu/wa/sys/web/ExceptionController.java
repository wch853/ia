package com.njfu.wa.sys.web;

import com.njfu.wa.sys.domain.util.Result;
import com.njfu.wa.sys.domain.util.ResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {

    @Autowired
    private ResultFactory resultFactory;

    private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);

    /**
     * 处理数据字符长度输入异常
     *
     * @return json message
     */
    @ExceptionHandler({DataIntegrityViolationException.class, BindException.class})
    public @ResponseBody
    Result dataIntegrityViolationException() {
        return resultFactory.failMessage("非法数据操作！");
    }

    /**
     * 统一异常处理
     *
     * @param e e
     * @return page
     */
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        log.error("e: {}, message: {}", e.getClass().getName(), e.getMessage());
        return "error";
    }
}
