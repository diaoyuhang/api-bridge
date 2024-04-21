package com.api.bridge.dto;

public class ResultDto<T> {

    private String msg;
    private Integer code;

    private T date;

    public static <T> ResultDto<T> createSuccess(T date){
        ResultDto<T> resultDto = new ResultDto<>();
        resultDto.code = 200;
        resultDto.date=date;
        return resultDto;
    }

    public static ResultDto<String> createFail(String message) {
        ResultDto<String> resultDto = new ResultDto<>();
        resultDto.code = 1000;
        resultDto.msg = message;
        return resultDto;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }
}
