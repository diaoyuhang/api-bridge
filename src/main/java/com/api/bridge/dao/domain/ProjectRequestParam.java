package com.api.bridge.dao.domain;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;

import java.util.Date;
import java.util.List;

/**
 * 项目请求参数配置
 * project_request_param
 */
public class ProjectRequestParam {

    public static final String PARAMETER_HEADER_TYPE="header";
    public static final Integer HEADER_TYPE=0;
    public static final String PARAMETER_COOKIE_TYPE="cookie";
    public static final Integer COOKIE_TYPE=1;

    /**
     * 主键
     */
    private Long id;

    /**
     * 项目id|project.id
     */
    private Long projectId;

    /**
     * 参数类型|0-请求头Header参数，1-cookie参数
     */
    private Integer type;

    /**
     * 参数名
     */
    private String name;

    /**
     * 参数是否必填
     */
    private Boolean required;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 编辑时间
     */
    private Date editTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改人
     */
    private String editor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public void addParametersForPathItem(PathItem pathItem) {
        Operation operation = null;

        if (pathItem.getGet() != null) {
            operation = pathItem.getGet();
        } else if (pathItem.getPost() != null) {
            operation = pathItem.getPost();
        } else if (pathItem.getDelete() != null) {
            operation = pathItem.getDelete();
        } else if (pathItem.getPut() != null) {
            operation = pathItem.getPut();
        }

        if (operation != null){
            List<Parameter> parameters = operation.getParameters();
            for (Parameter parameter : parameters) {
                if (this.name.equals(parameter.getName())){
                    return;
                }
            }
            parameters.add(converParameter());
        }
    }

    public Parameter converParameter(){
        Parameter parameter = new Parameter();
        parameter.setName(this.name);
        parameter.setRequired(this.required);

        if (this.getType().equals(HEADER_TYPE)){
            parameter.setIn(PARAMETER_HEADER_TYPE);

        }else if (this.getType().equals(COOKIE_TYPE)){
            parameter.setIn(PARAMETER_COOKIE_TYPE);
        }

        Schema schema = new Schema();
        schema.setType("string");
        parameter.setSchema(schema);

        return parameter;
    }
}