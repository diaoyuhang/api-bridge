package com.api.bridge.dto.ProjectRequestParam;

import com.api.bridge.dao.domain.ProjectRequestParam;
import com.api.bridge.utils.SecretUtil;
import org.springframework.beans.BeanUtils;

public class ProjectRequestParamResDto {

    private String id;

    private String projectId;


    private Integer type;

    private String name;

    private Boolean required;

    public static ProjectRequestParamResDto create(ProjectRequestParam projectRequestParam){
        ProjectRequestParamResDto projectRequestParamResDto = new ProjectRequestParamResDto();

        projectRequestParamResDto.setId(SecretUtil.encrypt(projectRequestParam.getId().toString()));
        projectRequestParamResDto.setProjectId(SecretUtil.encrypt(projectRequestParam.getProjectId().toString()));
        projectRequestParamResDto.setType(projectRequestParam.getType());
        projectRequestParamResDto.setName(projectRequestParam.getName());
        projectRequestParamResDto.setRequired(projectRequestParam.getRequired());

        return projectRequestParamResDto;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }
}
