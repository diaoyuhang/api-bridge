package com.api.bridge.constant;

public enum Status {
    ok(200, "success"),
    GLOBAL_ERROR(1000, "发生错误"),
    PARAM_ERROR(1001, "参数异常");

    private Integer statueCode;
    private String message;

    Status(Integer statueCode, String message) {
        this.statueCode = statueCode;
        this.message = message;
    }

    public Integer getStatueCode() {
        return statueCode;
    }

    public String getMessage() {
        return message;
    }
}
