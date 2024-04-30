package com.api.bridge.dto.permission;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserPermissionReqDto {

    @NotBlank(message = "projectId is empty")
    private String projectId;

    @NotNull(message = "permissionType is null")
    private Integer permissionType;
    @NotBlank(message = "userId is empty")
    private String userId;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Integer getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(Integer permissionType) {
        this.permissionType = permissionType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
