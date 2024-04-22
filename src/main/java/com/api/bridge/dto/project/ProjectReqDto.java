package com.api.bridge.dto.project;

import com.api.bridge.dto.validGroup.Delete;
import com.api.bridge.dto.validGroup.Select;
import com.api.bridge.dto.validGroup.Update;
import jakarta.validation.constraints.NotBlank;

public class ProjectReqDto {

    @NotBlank(message = "project id is empty",groups = {Select.class, Delete.class})
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
}
