package com.api.bridge.controller;

import com.api.bridge.constant.Status;
import com.api.bridge.dao.domain.Project;
import com.api.bridge.dto.ResultDto;
import com.api.bridge.dto.permission.PermissionPathType;
import com.api.bridge.dto.project.ProjectReqDto;
import com.api.bridge.dto.project.ProjectResDto;
import com.api.bridge.dto.validGroup.Delete;
import com.api.bridge.dto.validGroup.Insert;
import com.api.bridge.dto.validGroup.Update;
import com.api.bridge.service.AuthorizationService;
import com.api.bridge.service.ProjectService;
import com.api.bridge.utils.SecretUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping("/createProject")
    public ResultDto<ProjectResDto> createProject(@RequestBody @Validated({Insert.class}) ProjectReqDto projectReqDto){
        Project project = projectService.createProject(projectReqDto);
        ProjectResDto projectResDto = ProjectResDto.create(project);
        return ResultDto.createSuccess(projectResDto);
    }

    @GetMapping("/projectList")
    public ResultDto<List<ProjectResDto>> projectList() {
        List<Project> projects = projectService.getProjectList();
        ArrayList<ProjectResDto> res = new ArrayList<>();
        projects.forEach(p -> res.add(ProjectResDto.create(p)));

        return ResultDto.createSuccess(res);
    }

    @PostMapping("/editProject")
    public ResultDto<String> editProject(@RequestBody @Validated({Update.class}) ProjectReqDto projectReqDto){
        authorizationService.validate(Long.parseLong(SecretUtil.decrypt(projectReqDto.getProjectId())), PermissionPathType.PROJECT_EDIT);
        projectService.editInfo(projectReqDto);
        return ResultDto.createSuccess(Status.ok.getMessage());
    }

    @PostMapping("/deleteProject")
    public ResultDto<String> deleteProject(@RequestBody @Validated({Delete.class}) ProjectReqDto projectReqDto){
        Long projectId = Long.parseLong(SecretUtil.decrypt(projectReqDto.getProjectId()));
        authorizationService.validate(projectId, PermissionPathType.PROJECT_DELETE);
        projectService.deleteProject(projectId);
        return ResultDto.createSuccess(Status.ok.getMessage());
    }
}
