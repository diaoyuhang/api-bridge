package com.api.bridge.service.impl;

import com.api.bridge.dao.PermissionPathDao;
import com.api.bridge.dao.ProjectDao;
import com.api.bridge.dao.UserPermissionDao;
import com.api.bridge.dao.domain.PermissionPath;
import com.api.bridge.dao.domain.Project;
import com.api.bridge.dao.domain.User;
import com.api.bridge.dao.domain.UserPermission;
import com.api.bridge.dto.permission.PermissionPathType;
import com.api.bridge.dto.project.ProjectReqDto;
import com.api.bridge.service.ProjectService;
import com.api.bridge.utils.UserHelperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final static Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private PermissionPathDao permissionPathDao;
    @Autowired
    private UserPermissionDao userPermissionDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Project createProject(ProjectReqDto projectReqDto) {
        Project project = projectReqDto.convertProject();
        projectDao.insert(project);

        List<PermissionPath> permissionPathList = project.generatePermissionPath();
        permissionPathDao.batchInsert(permissionPathList);

        List<Long> permissionIds = permissionPathDao.selectIdByProjectId(project.getId());
        List<UserPermission> userPermissionList = UserPermission.create(permissionIds);
        userPermissionDao.batchInsert(userPermissionList);

        return project;
    }

    @Override
    public List<Project> getProjectList() {
        User user = UserHelperUtil.getUser();
        List<Long> projectIds = permissionPathDao.selectProjectIdByUserIdAndPathType(user.getId(), PermissionPathType.PROJECT_VIEW.getType());
        if (projectIds.isEmpty()){
            return Collections.EMPTY_LIST;
        }
        List<Project> projects = projectDao.selectByIds(projectIds);
        return projects;
    }

    @Override
    public void editInfo(ProjectReqDto projectReqDto) {
        Project project = projectReqDto.convertProject();
        Project oldProject = projectDao.selectByPrimaryKey(project.getId());
        Assert.isTrue(oldProject != null, "项目id不存在");

        oldProject.setName(project.getName());
        oldProject.setDescription(project.getDescription());
        UserHelperUtil.fillEditInfo(oldProject);
        projectDao.updateByPrimaryKey(oldProject);
    }

    @Override
    public void deleteProject(Long projectId) {
        Project oldProject = projectDao.selectByPrimaryKey(projectId);
        Assert.isTrue(oldProject != null, "项目id不存在");
        projectDao.deleteByPrimaryKey(projectId);
    }
}
