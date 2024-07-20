package com.api.bridge.dto.permission;

import java.util.ArrayList;
import java.util.List;

public class ProjectAutoInfoResDto {
    private String email;

    private List<Integer> permissionType = new ArrayList<>();


    public ProjectAutoInfoResDto() {
    }

    public ProjectAutoInfoResDto(String email, List<Integer> permissionType) {
        this.email = email;
        this.permissionType = permissionType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Integer> getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(List<Integer> permissionType) {
        this.permissionType = permissionType;
    }
}
