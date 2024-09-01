package com.api.bridge.dto.ProjectRequestParam;

import com.api.bridge.dao.domain.ProjectRequestParam;
import com.api.bridge.dto.validGroup.Delete;
import com.api.bridge.dto.validGroup.Insert;
import com.api.bridge.dto.validGroup.Update;
import com.api.bridge.utils.SecretUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProjectRequestParamReqDto {

    @NotBlank(message = "id is empty",groups = {Delete.class,Update.class})
    private String id;

    @NotBlank(message = "project id is empty",groups = {Insert.class, Update.class,Delete.class})
    private String projectId;

    @NotNull(message = "type is null",groups = {Insert.class, Update.class})
    private Integer type;

    @NotBlank(message = "name is empty",groups = {Insert.class, Update.class})
    private String name;

    @NotNull(message = "required is null",groups = {Insert.class, Update.class})
    private Boolean required;

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

    public ProjectRequestParam convertProjectRequestParam() {
        ProjectRequestParam res= new ProjectRequestParam();

        if (StringUtils.isNotEmpty(this.id)){
            res.setId(Long.valueOf(SecretUtil.decrypt(this.id)));
        }

        if (StringUtils.isNotEmpty(this.projectId)){
            res.setProjectId(Long.valueOf(SecretUtil.decrypt(this.projectId)));
        }

        res.setType(this.type);
        res.setName(this.name);
        res.setRequired(this.required);

        return res;
    }
}
