package com.api.bridge.dto.permission;

public class PermissionPathTypeResDto {
    private Integer type;
    private String desc;

    public PermissionPathTypeResDto() {
    }

    public PermissionPathTypeResDto(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
