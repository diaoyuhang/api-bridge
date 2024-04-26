package com.api.bridge.service.impl;

import com.api.bridge.dao.UserPermissionDao;
import com.api.bridge.dao.domain.User;
import com.api.bridge.dto.permission.PermissionPathType;
import com.api.bridge.service.AuthorizationService;
import com.api.bridge.utils.UserHelperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    private final static Logger logger = LoggerFactory.getLogger(AuthorizationServiceImpl.class);

    @Autowired
    private UserPermissionDao userPermissionDao;
    @Override
    public void validate(Long projectId, PermissionPathType permissionPathType) {
        User user = UserHelperUtil.getUser();
        int num = userPermissionDao.selectCountByProjectIdAndPathTypeAndUserId(projectId,permissionPathType.getType(),user.getId());
        Assert.isTrue(num > 0, "没有权限");
    }
}
