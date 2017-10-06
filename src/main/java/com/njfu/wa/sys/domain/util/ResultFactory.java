package com.njfu.wa.sys.domain.util;

import com.njfu.wa.sys.enums.ResultEnum;
import org.springframework.stereotype.Service;

/**
 * 结果工厂类，用于返回结果
 */
@Service
public class ResultFactory<T> {

    public ResultFactory() {
    }

    public Result successMessage(String message) {
        return new Result(ResultEnum.SUCCESS.getCode(), message);
    }

    public Result failMessage(String message) {
        return new Result(ResultEnum.FAIL.getCode(), message);
    }

    public Result<T> dataResult(T data) {
        return new Result<>(ResultEnum.DATA.getCode(), ResultEnum.DATA.getInfo(), data);
    }

    public Result<T> warnResult(T data) {
        return new Result<>(ResultEnum.WARN.getCode(), ResultEnum.WARN.getInfo(), data);
    }

}
