package com.api.bridge.service;

import com.api.bridge.dto.permission.UserPermissionReqDto;

public interface UserPermissionService {
    void addPermission(UserPermissionReqDto userPermissionReqDto);

    void deletePermission(UserPermissionReqDto userPermissionReqDto);
}
