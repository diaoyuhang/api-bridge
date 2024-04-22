package com.api.bridge.dto.permission;

public enum PermissionPathType {

    PROJECT_VIEW(1,"项目查看"),
    PROJECT_EDIT(2,"项目编辑"),
    PROJECT_DELETE(3,"项目删除"),
    API_UPLOAD(4,"上传接口"),
    API_DELETE(5,"接口删除");

    PermissionPathType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    private Integer type;
    private String desc;

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

}
