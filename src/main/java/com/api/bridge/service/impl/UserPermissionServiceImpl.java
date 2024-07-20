package com.api.bridge.service.impl;

import com.api.bridge.dao.PermissionPathDao;
import com.api.bridge.dao.UserDao;
import com.api.bridge.dao.UserPermissionDao;
import com.api.bridge.dao.domain.PermissionPath;
import com.api.bridge.dao.domain.UserPermission;
import com.api.bridge.dto.permission.ProjectAutoInfoResDto;
import com.api.bridge.dto.permission.UserPermissionInfoDto;
import com.api.bridge.dto.permission.UserPermissionReqDto;
import com.api.bridge.service.UserPermissionService;
import com.api.bridge.utils.SecretUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserPermissionServiceImpl implements UserPermissionService {
    private final static Logger logger = LoggerFactory.getLogger(UserPermissionServiceImpl.class);

    @Autowired
    private PermissionPathDao permissionPathDao;
    @Autowired
    private UserPermissionDao userPermissionDao;
    @Autowired
    private UserDao userDao;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addPermission(UserPermissionReqDto userPermissionReqDto) {
        Long projectId = Long.parseLong(SecretUtil.decrypt(userPermissionReqDto.getProjectId()));
        Long userId = userDao.selectUserIdByEmail(userPermissionReqDto.getEmail());
        Assert.notNull(userId, userPermissionReqDto.getEmail() + "用户邮箱不存在");

        List<PermissionPath> permissionPaths = permissionPathDao.selectByProjectId(projectId);
        Assert.isTrue(permissionPaths.size() > 0, "项目权限为空");
        List<Long> needDeletePermissionIds = permissionPaths.stream().filter(pp -> !userPermissionReqDto.getPermissionTypes().contains(pp.getPathType()))
                .map(PermissionPath::getId).collect(Collectors.toList());
        if (!needDeletePermissionIds.isEmpty()) {
            userPermissionDao.deleteByUseIdAndPermissionId(userId, needDeletePermissionIds);
        }

        List<Long> needAddPermissionIds = permissionPaths.stream().filter(pp -> userPermissionReqDto.getPermissionTypes().contains(pp.getPathType()))
                .map(PermissionPath::getId).collect(Collectors.toList());
        if (!needAddPermissionIds.isEmpty()) {
            List<Long> existPermissionIds = userPermissionDao.selectExistPermissionIdByUserIdAndPermissions(userId, needAddPermissionIds);
            needAddPermissionIds.removeAll(existPermissionIds);

            if (!needAddPermissionIds.isEmpty()) {
                List<UserPermission> userPermissions = needAddPermissionIds.stream()
                        .map(id -> UserPermission.create(userId, id)).collect(Collectors.toList());
                userPermissionDao.batchInsert(userPermissions);
            }
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePermission(UserPermissionReqDto userPermissionReqDto) {
        Long projectId = Long.parseLong(SecretUtil.decrypt(userPermissionReqDto.getProjectId()));
        Long userId = userDao.selectUserIdByEmail(userPermissionReqDto.getEmail());
        Assert.notNull(userId, userPermissionReqDto.getEmail() + "用户邮箱不存在");

        List<Long> permissionIds = permissionPathDao.selectIdByProjectId(projectId);
        Assert.isTrue(permissionIds.size() > 0, "权限id为null");
        userPermissionDao.deleteByUseIdAndPermissionId(userId, permissionIds);
    }

    @Override
    public List<ProjectAutoInfoResDto> getProjectAuthInfo(Long projectId, Long userId) {
        List<PermissionPath> permissionPaths = permissionPathDao.selectByProjectId(projectId);
        Assert.isTrue(!CollectionUtils.isEmpty(permissionPaths),projectId+" 未查询到权限");
        Map<Long, Integer> permissionIdMapTypeId = new HashMap<>();
        permissionPaths.forEach(pp->permissionIdMapTypeId.put(pp.getId(),pp.getPathType()));

        List<UserPermissionInfoDto> userPermissionInfoDtos = userPermissionDao.selectEmailAndPermissionIdByPermissionId(permissionIdMapTypeId.keySet());
        Map<String, List<UserPermissionInfoDto>> map = userPermissionInfoDtos.stream().collect(Collectors.groupingBy(UserPermissionInfoDto::getEmail));

        List<ProjectAutoInfoResDto> res = new ArrayList<>();
        for (Map.Entry<String, List<UserPermissionInfoDto>> entry : map.entrySet()) {
            List<Integer> permissionTypes = entry.getValue().stream().map(o -> permissionIdMapTypeId.get(o.getPermissionId())).collect(Collectors.toList());
            res.add(new ProjectAutoInfoResDto(entry.getKey(),permissionTypes));
        }
        return res;
    }
}
