package com.api.bridge.service.impl;

import com.api.bridge.dao.PermissionPathDao;
import com.api.bridge.dao.UserPermissionDao;
import com.api.bridge.dao.domain.UserPermission;
import com.api.bridge.dto.permission.UserPermissionReqDto;
import com.api.bridge.service.UserPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserPermissionServiceImpl implements UserPermissionService {
    private final static Logger logger = LoggerFactory.getLogger(UserPermissionServiceImpl.class);

    @Autowired
    private PermissionPathDao permissionPathDao;
    @Autowired
    private UserPermissionDao userPermissionDao;
    @Override
    public void addPermission(UserPermissionReqDto userPermissionReqDto) {
        Long projectId = Long.parseLong(userPermissionReqDto.getProjectId());
        Long userId = Long.parseLong(userPermissionReqDto.getUserId());
        Long permissionId = permissionPathDao.selectIdByProjectIdAndType(projectId,userId);

        Assert.notNull(permissionId,"权限id为null");
        UserPermission userPermission = UserPermission.create(userId, permissionId);

        userPermissionDao.insert(userPermission);
    }

    @Override
    public void deletePermission(UserPermissionReqDto userPermissionReqDto) {
        Long projectId = Long.parseLong(userPermissionReqDto.getProjectId());
        Long userId = Long.parseLong(userPermissionReqDto.getUserId());
        Long permissionId = permissionPathDao.selectIdByProjectIdAndType(projectId,userId);

        Assert.notNull(permissionId,"权限id为null");
        userPermissionDao.deleteByUseIdAndPermissionId(userId,permissionId);
    }
}
