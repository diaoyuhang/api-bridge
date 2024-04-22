package com.api.bridge.service;

import com.api.bridge.dao.domain.Project;
import com.api.bridge.dto.project.ProjectReqDto;

import java.util.List;

public interface ProjectService {
    Project createProject(ProjectReqDto projectReqDto);

    List<Project> getProjectList();

    void editInfo(ProjectReqDto projectReqDto);
}
