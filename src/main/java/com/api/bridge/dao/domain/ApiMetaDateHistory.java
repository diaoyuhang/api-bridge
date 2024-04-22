package com.api.bridge.dao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * api_meta_date_history
 *
 * @author
 */
public class ApiMetaDateHistory {
    //1-新增，2-更新，3-删除
    public static final Integer ADD_OPERATION_TYPE = 1;
    public static final Integer UPDATE_OPERATION_TYPE = 2;
    public static final Integer DELETE_OPERATION_TYPE = 3;

    /**
     * 历史记录主键
     */
    private Long id;

    /**
     * 接口id|api_meta_date.id
     */
    private Long apiId;

    /**
     * 组id|tag_group.id
     */
    private String tagId;

    /**
     * 接口请求路径
     */
    private String path;

    /**
     * 请求方法|GET POST PUT DELETE
     */
    private String method;

    /**
     * 接口名
     */
    private String summary;
    /**
     * 操作类型|1-新增，2-更新，3-删除
     */
    private Integer operationType;

    /**
     * api元数据
     */
    private String metaDate;

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

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getMetaDate() {
        return metaDate;
    }

    public void setMetaDate(String metaDate) {
        this.metaDate = metaDate;
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
}