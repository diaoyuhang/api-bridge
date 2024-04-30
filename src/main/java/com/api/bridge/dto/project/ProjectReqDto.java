package com.api.bridge.dto.project;

import com.api.bridge.dao.domain.Project;
import com.api.bridge.dto.validGroup.Delete;
import com.api.bridge.dto.validGroup.Select;
import com.api.bridge.dto.validGroup.Update;
import com.api.bridge.utils.SecretUtil;
import com.api.bridge.utils.UserHelperUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;

public class ProjectReqDto {

    @NotBlank(message = "project id is empty",groups = {Select.class, Delete.class, Update.class})
    private String projectId;
    @NotBlank(message = "name is empty",groups = {Integer.class, Update.class})
    private String name;

    @NotBlank(message = "description is empty",groups = {Integer.class, Update.class})
    private String description;

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

    public Project convertProject() {
        Project project = new Project();
        project.setName(this.name);
        project.setDescription(this.description);

        if (!StringUtils.isEmpty(this.projectId)){
            project.setId(Long.parseLong(SecretUtil.decrypt(this.projectId)));
        }

        UserHelperUtil.fillEditInfo(project);
        UserHelperUtil.fillCreateInfo(project);
        return project;
    }
}
