package com.api.bridge.config;

import com.api.bridge.dto.ResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionConfig {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionConfig.class);

    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public ResultDto<String> exceptionHandler(Exception e){
        logger.error("exceptionHandler:"+e.getMessage(),e);
        return ResultDto.createFail(e.getMessage());
    }

}
