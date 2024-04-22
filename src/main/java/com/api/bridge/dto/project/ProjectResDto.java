package com.api.bridge.dto.project;

import com.api.bridge.dao.domain.Project;
import com.api.bridge.utils.SecretUtil;

public class ProjectResDto {

    private String name;

    private String description;

    private String projectId;

    public static ProjectResDto create(Project project) {
        ProjectResDto projectResDto = new ProjectResDto();
        projectResDto.setName(project.getName());
        projectResDto.setDescription(project.getDescription());
        projectResDto.setProjectId(SecretUtil.encrypt(project.getId().toString()));
        return projectResDto;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
