package com.api.bridge.dto.permission;


import com.api.bridge.dto.validGroup.Delete;
import com.api.bridge.dto.validGroup.Insert;
import com.api.bridge.dto.validGroup.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class UserPermissionReqDto {

    @NotBlank(message = "projectId is empty",groups = {Insert.class, Delete.class})
    private String projectId;

    @Size(min = 1,message = "permissionTypes is empty",groups = {Insert.class})
    private List<Integer> permissionTypes;
    @NotBlank(message = "email is empty",groups = {Insert.class, Delete.class})
    private String email;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public List<Integer> getPermissionTypes() {
        return permissionTypes;
    }

    public void setPermissionTypes(List<Integer> permissionTypes) {
        this.permissionTypes = permissionTypes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
