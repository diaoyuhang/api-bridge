package com.api.bridge.config;

import com.api.bridge.constant.BaseConstant;
import com.api.bridge.constant.Status;
import com.api.bridge.dto.ResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionConfig {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionConfig.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultDto<String> exceptionHandler(Exception e) {
        logger.error("exceptionHandler:" + e.getMessage(), e);
        return ResultDto.createFail(Status.GLOBAL_ERROR);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultDto<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getAllErrors().stream().map(t -> t.getDefaultMessage()).collect(Collectors.joining(BaseConstant.COMMA_SEPARATOR));
        return ResultDto.createFail(Status.PARAM_ERROR, errorMessage);
    }

}