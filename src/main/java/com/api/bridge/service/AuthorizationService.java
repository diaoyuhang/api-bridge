package com.api.bridge.service;

import com.api.bridge.dto.permission.PermissionPathType;

public interface AuthorizationService {
    void validate(Long projectId, PermissionPathType permissionPathType);
}
