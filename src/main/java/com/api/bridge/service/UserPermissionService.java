package com.api.bridge.service;

import com.api.bridge.dto.permission.ProjectAutoInfoResDto;
import com.api.bridge.dto.permission.UserPermissionReqDto;

import java.util.List;

public interface UserPermissionService {
    void addPermission(UserPermissionReqDto userPermissionReqDto);

    void deletePermission(UserPermissionReqDto userPermissionReqDto);

    List<ProjectAutoInfoResDto> getProjectAuthInfo(Long projectId, Long userId);
}
