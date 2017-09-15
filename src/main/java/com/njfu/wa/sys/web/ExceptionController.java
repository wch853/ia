package com.njfu.wa.sys.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionController {

    private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        log.error(e.getMessage());
        return "error";
    }
}
